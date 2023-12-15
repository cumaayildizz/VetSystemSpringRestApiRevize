VetSystemSpringRestApi
Proje Java+Maven Springboot framework kullanılarak geliştirilen bir rest api projesidir. 
Projede Hayvanlar(Animal) - Müşteri(Customer) - Aşı(Vaccine) - Müsait Zaman(AvailiableDate) 
- Doktor(Doctor) - Randevu (Appoıntment) Sınıfları üzerinden kurgulanıp tsarlanmıştır.

Projede Mimarisi

-Entities(Model)

-Dto -Request(Save-Update) -Response

-Dao -Repository Interface extents Jpa

-Business -Servıce Interface -Manager Class ımplements Servıce

-Api(Controller)

-Core(Helper-Configurations)
katmanları üzerinden kurgulanmıştır. Sınıflar arası geçiş Dependenc İnjection ile gerçekleştirilmiştir.

Not: Localhost 8082 portunda çalışır.

Animal End points;

POST
http://localhost:8082/v1/vetSystem/animals 
PUT
http://localhost:8082/v1/vetSystem/animals/{id} 
DELETE
http://localhost:8082/v1/vetSystem/animals/{id} 
GET 
http://localhost:8082/v1/vetSystem/animals/{id} 
http://localhost:8082/v1/vetSystem/animals 
http://localhost:8082/v1/vetSystem/animals/cursor 
http://localhost:8082/v1/vetSystem/animals/{animalId}/vaccines 
http://localhost:8082/v1/vetSystem/animals/search
http://localhost:8082/v1/vetSystem/animals/ownerName/{ownerName} 
http://localhost:8082/v1/vetSystem/animals/owner/{ownerId} 
http://localhost:8082/v1/vetSystem/animals/gender/{gender} 
http://localhost:8082/v1/vetSystem/animals/filter/{name} 
http://localhost:8082/v1/vetSystem/animals/birthdate 
http://localhost:8082/v1/vetSystem/animals/animalName/{animalName}

Customer End Points;

POST
http://localhost:8082/v1/vetSystem/customers 
PUT 
http://localhost:8082/v1/vetSystem/customers/{id} 
DELETE 
http://localhost:8082/v1/vetSystem/customers/{id} 
GET 
http://localhost:8082/v1/vetSystem/customers/{id} 
http://localhost:8082/v1/vetSystem/customers 
http://localhost:8082/v1/vetSystem/customers/{customerId}/animals 
http://localhost:8082/v1/vetSystem/customers/name/{name} 
http://localhost:8082/v1/vetSystem/customers/cursor

Doctor End Points;

POST 
http://localhost:8082/v1/vetSystem/doctors 
PUT 
http://localhost:8082/v1/vetSystem/doctors/{id} 
DELETE 
http://localhost:8082/v1/vetSystem/doctors/{id} 
GET 
http://localhost:8082/v1/vetSystem/doctors/{id} 
http://localhost:8082/v1/vetSystem/doctors 
http://localhost:8082/v1/vetSystem/doctors/cursor

AvailiableDate End Points;

POST 
http://localhost:8082/v1/vetSystem/doctors 
PUT 
http://localhost:8082/v1/vetSystem/doctors/{id}
DELETE
http://localhost:8082/v1/vetSystem/doctors/{id} 
GET 
http://localhost:8082/v1/vetSystem/available-dates/{id} 
http://localhost:8082/v1/vetSystem/available-dates 
http://localhost:8082/v1/vetSystem/available-dates/cursor 
http://localhost:8082/v1/vetSystem/available-dates/doctor/{doctorId} 
http://localhost:8082/v1/vetSystem/available-dates/doctor/{doctorId}/availability 
http://localhost:8082/v1/vetSystem/available-dates/doctor/{doctorId}/availability-range

Appointment End Points;

POST 
http://localhost:8082/v1/vetSystem/appointments 
PUT 
http://localhost:8082/v1/vetSystem/appointments/{id} 
DELETE 
http://localhost:8082/v1/vetSystem/appointments/{id} 
GET
http://localhost:8082/v1/vetSystem/appointments/{id} 
http://localhost:8082/v1/vetSystem/appointments
http://localhost:8082/v1/vetSystem/appointments/cursor 
http://localhost:8082/v1/vetSystem/appointments/filter/doctor/{doctorId} 
http://localhost:8082/v1/vetSystem/appointments/filter/date 
http://localhost:8082/v1/vetSystem/appointments/filter/animal 
http://localhost:8082/v1/vetSystem/appointments/filter/animal/{animalId} 
http://localhost:8082/v1/vetSystem/appointments/doctor 
http://localhost:8082/v1/vetSystem/appointments/animal
http://localhost:8082/v1/vetSystem/appointments/filter/animal/doctor/date-range

Vaccine End Points;

POST 
http://localhost:8082/v1/vetSystem/vaccines 
PUT 
http://localhost:8082/v1/vetSystem/vaccines/{id} 
DELETE 
http://localhost:8082/v1/vetSystem/vaccines/{id} 
GET 
http://localhost:8082/v1/vetSystem/vaccines/{id} 
http://localhost:8082/v1/vetSystem/vaccines 
http://localhost:8082/v1/vetSystem/vaccines/upcoming-vaccines 
http://localhost:8082/v1/vetSystem/vaccines/date-range 
http://localhost:8082/v1/vetSystem/vaccines/cursor 
http://localhost:8082/v1/vetSystem/vaccines/animals/{animalId}/vaccines 
http://localhost:8082/v1/vetSystem/vaccines/animal/filter/date
