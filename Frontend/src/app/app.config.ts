// Provides AppModule so bootstrapApplication() in main.ts can use it.
import { importProvidersFrom } from '@angular/core';
import { AppModule } from './app.module';

export const appConfig = {
  providers: [
    importProvidersFrom(AppModule)
  ]
};
