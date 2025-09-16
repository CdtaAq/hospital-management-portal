-- doctor
CREATE TABLE doctor (
  id BIGSERIAL PRIMARY KEY,
  first_name VARCHAR(100) NOT NULL,
  last_name VARCHAR(100),
  specialty VARCHAR(100) NOT NULL,
  experience_years SMALLINT,
  fees DECIMAL(10,2) DEFAULT 0,
  phone VARCHAR(20),
  email VARCHAR(200),
  bio TEXT,
  rating DECIMAL(2,1) DEFAULT 0,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- disease
CREATE TABLE disease (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(150) NOT NULL UNIQUE,
  category VARCHAR(100),
  description TEXT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- treatment
CREATE TABLE treatment (
  id BIGSERIAL PRIMARY KEY,
  disease_id BIGINT REFERENCES disease(id) ON DELETE CASCADE,
  name VARCHAR(200) NOT NULL,
  estimated_time_days INT,
  estimated_cost DECIMAL(10,2),
  description TEXT
);

-- patient
CREATE TABLE patient (
  id BIGSERIAL PRIMARY KEY,
  first_name VARCHAR(100) NOT NULL,
  last_name VARCHAR(100),
  dob DATE,
  gender VARCHAR(20),
  phone VARCHAR(20),
  email VARCHAR(200),
  address TEXT,
  medical_history TEXT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- doctor_disease (many-to-many)
CREATE TABLE doctor_disease (
  doctor_id BIGINT REFERENCES doctor(id) ON DELETE CASCADE,
  disease_id BIGINT REFERENCES disease(id) ON DELETE CASCADE,
  PRIMARY KEY (doctor_id, disease_id)
);

-- doctor_availability
CREATE TABLE doctor_availability (
  id BIGSERIAL PRIMARY KEY,
  doctor_id BIGINT REFERENCES doctor(id) ON DELETE CASCADE,
  available_date DATE NOT NULL,
  start_time TIME NOT NULL,
  end_time TIME NOT NULL,
  slot_status VARCHAR(20) DEFAULT 'OPEN', -- OPEN, BOOKED, BLOCKED
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- appointment
CREATE TABLE appointment (
  id BIGSERIAL PRIMARY KEY,
  patient_id BIGINT REFERENCES patient(id) ON DELETE CASCADE,
  doctor_id BIGINT REFERENCES doctor(id) ON DELETE CASCADE,
  disease_id BIGINT REFERENCES disease(id),
  scheduled_date DATE NOT NULL,
  scheduled_start TIME,
  scheduled_end TIME,
  status VARCHAR(30) DEFAULT 'SCHEDULED', -- SCHEDULED, CONFIRMED, RESCHEDULED, CANCELLED, COMPLETED, EMERGENCY
  reason TEXT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- prescription
CREATE TABLE prescription (
  id BIGSERIAL PRIMARY KEY,
  appointment_id BIGINT REFERENCES appointment(id) ON DELETE CASCADE,
  medicines TEXT, -- JSON or plain text list
  instructions TEXT,
  issued_by BIGINT REFERENCES doctor(id),
  issued_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- payment
CREATE TABLE payment (
  id BIGSERIAL PRIMARY KEY,
  appointment_id BIGINT REFERENCES appointment(id) ON DELETE CASCADE,
  amount DECIMAL(10,2),
  method VARCHAR(50),
  status VARCHAR(30) DEFAULT 'PENDING', -- PENDING, PAID, REFUNDED
  transaction_ref VARCHAR(200),
  paid_at TIMESTAMP
);

-- review
CREATE TABLE review (
  id BIGSERIAL PRIMARY KEY,
  doctor_id BIGINT REFERENCES doctor(id) ON DELETE CASCADE,
  patient_id BIGINT REFERENCES patient(id) ON DELETE SET NULL,
  rating SMALLINT CHECK (rating >= 1 AND rating <= 5),
  comment TEXT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- admission
CREATE TABLE admission (
  id BIGSERIAL PRIMARY KEY,
  patient_id BIGINT REFERENCES patient(id) ON DELETE CASCADE,
  appointment_id BIGINT REFERENCES appointment(id) ON DELETE SET NULL,
  admitted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  discharged_at TIMESTAMP,
  ward VARCHAR(100),
  notes TEXT
);
