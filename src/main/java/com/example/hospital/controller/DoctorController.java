@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
    private final DoctorService service;
    public DoctorController(DoctorService service){ this.service = service; }

    @PostMapping
    public ResponseEntity<Doctor> create(@Valid @RequestBody Doctor d){ 
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(d));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> get(@PathVariable Long id){
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Doctor> list(@RequestParam(required=false) String specialty){
        if(specialty==null) return service.findAll();
        return service.findBySpecialty(specialty);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> update(@PathVariable Long id, @RequestBody Doctor updated){
        return service.findById(id).map(d -> {
           d.setFirstName(updated.getFirstName());
           d.setLastName(updated.getLastName());
           d.setSpecialty(updated.getSpecialty());
           d.setExperienceYears(updated.getExperienceYears());
           d.setFees(updated.getFees());
           // ... other fields
           service.update(d);
           return ResponseEntity.ok(d);
        }).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
