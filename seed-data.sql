-- ============================================================
-- SEED DATA FOR HOSPITAL MANAGEMENT SYSTEM (HMS)
-- 5 INSERTS PER TABLE
-- ============================================================

-- 1. ROLE (independent)
INSERT INTO role (role_name) VALUES
('ADMIN'),
('DOCTOR'),
('NURSE'),
('RECEPTIONIST'),
('LAB_TECHNICIAN');

-- 2. DEPARTMENT (independent)
INSERT INTO department (dept_name, description, created_at) VALUES
('Cardiology', 'Heart and cardiovascular system', '2025-01-10 08:00:00'),
('Pediatrics', 'Child healthcare', '2025-01-10 08:00:00'),
('Orthopedics', 'Bones and joints', '2025-01-10 08:00:00'),
('Neurology', 'Brain and nervous system', '2025-02-01 08:00:00'),
('General Medicine', 'Primary care and internal medicine', '2025-01-10 08:00:00');

-- 3. USER (independent, password = bcrypt for "password123")
INSERT INTO user (user_name, full_name, password, email, designation, enabled) VALUES
('admin', 'Admin User', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'admin@hms.com', 'System Administrator', 1),
('dr.smith', 'Dr. John Smith', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'john.smith@hms.com', 'Cardiologist', 1),
('dr.garcia', 'Dr. Maria Garcia', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'maria.garcia@hms.com', 'Pediatrician', 1),
('nurse.amy', 'Amy Johnson', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'amy.johnson@hms.com', 'Head Nurse', 1),
('reception.claire', 'Claire Davis', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'claire.davis@hms.com', 'Receptionist', 1);

-- 4. USER_ROLE (references user, role)
INSERT INTO user_role (user_id, role_id) VALUES
(1, 1),  -- admin -> ADMIN
(2, 2),  -- dr.smith -> DOCTOR
(3, 2),  -- dr.garcia -> DOCTOR
(4, 3),  -- nurse.amy -> NURSE
(5, 4);  -- reception.claire -> RECEPTIONIST

-- 5. DOCTOR (references department)
INSERT INTO doctor (d_name, specialization, consult_charge, salary, dept_id) VALUES
('Dr. John Smith', 'Cardiology', 500.00, 180000.00, 1),
('Dr. Maria Garcia', 'Pediatrics', 400.00, 160000.00, 2),
('Dr. Robert Chen', 'Orthopedics', 450.00, 170000.00, 3),
('Dr. Sarah Williams', 'Neurology', 600.00, 200000.00, 4),
('Dr. James Wilson', 'General Medicine', 350.00, 150000.00, 5);

-- 6. PATIENT (independent)
INSERT INTO patient (patient_name, age, gender, phone, email, address, admission_date, patient_amt) VALUES
('Alice Thompson', 35, 'Female', '555-0101', 'alice.t@email.com', '123 Oak St, Springfield', '2026-06-01 09:30:00', 0.00),
('Bob Martinez', 58, 'Male', '555-0102', 'bob.m@email.com', '456 Pine Ave, Springfield', '2026-06-10 14:00:00', 250.00),
('Carol Nguyen', 28, 'Female', '555-0103', 'carol.n@email.com', '789 Maple Dr, Springfield', '2026-06-15 11:00:00', 0.00),
('David Kim', 72, 'Male', '555-0104', 'david.k@email.com', '321 Elm St, Springfield', '2026-06-18 10:00:00', 1200.00),
('Eva Patel', 45, 'Female', '555-0105', 'eva.p@email.com', '654 Cedar Ln, Springfield', '2026-06-20 08:30:00', 0.00);

-- 7. APPOINTMENT (references doctor, patient)
INSERT INTO appointment (patient_id, doctor_id, appointment_date, appointment_time, reason, status, created_at) VALUES
(1, 1, '2026-07-01', '09:00:00', 'Chest pain evaluation', 'SCHEDULED', '2026-06-19'),
(2, 5, '2026-07-02', '10:30:00', 'Regular checkup', 'SCHEDULED', '2026-06-19'),
(3, 2, '2026-07-03', '14:00:00', 'Child vaccination', 'SCHEDULED', '2026-06-19'),
(4, 4, '2026-07-05', '11:00:00', 'Memory loss concerns', 'SCHEDULED', '2026-06-20'),
(5, 3, '2026-07-06', '15:30:00', 'Knee pain', 'SCHEDULED', '2026-06-20');

-- 8. ROOM (references department)
INSERT INTO room (room_number, room_type, charge_per_day, is_available, dept_id) VALUES
('101', 'General Ward', 200.00, 1, 5),
('201', 'Private', 500.00, 1, 1),
('202', 'Private', 500.00, 1, 1),
('301', 'ICU', 1500.00, 1, 5),
('302', 'ICU', 1500.00, 0, 2);

-- 9. BED (references room, patient nullable)
INSERT INTO bed (bed_number, status, room_id, patient_id) VALUES
('A1', 'Available', 1, NULL),
('A2', 'Available', 1, NULL),
('B1', 'Occupied', 2, 2),
('C1', 'Available', 4, NULL),
('B2', 'Available', 3, NULL);

-- 10. MEDICINE (independent)
INSERT INTO medicine (name, brand, category, description, price, created_at) VALUES
('Amoxicillin', 'Pfizer', 'Antibiotic', 'Broad-spectrum antibiotic', 15.99, '2025-06-01 08:00:00'),
('Lisinopril', 'AstraZeneca', 'Antihypertensive', 'Blood pressure medication', 22.50, '2025-06-01 08:00:00'),
('Metformin', 'Merck', 'Antidiabetic', 'Diabetes management', 18.75, '2025-06-01 08:00:00'),
('Ibuprofen', 'Bayer', 'Anti-inflammatory', 'Pain and inflammation relief', 8.99, '2025-06-01 08:00:00'),
('Omeprazole', 'GSK', 'Antacid', 'Acid reflux treatment', 14.25, '2025-06-01 08:00:00');

-- 11. SUPPLIER (independent)
INSERT INTO supplier (name, contact_person, phone, email, address) VALUES
('MedSupply Co.', 'Mark Lewis', '555-0201', 'mark@medsupply.com', '100 Industrial Blvd, Springfield'),
('PharmaCare Inc.', 'Laura Adams', '555-0202', 'laura@pharmacare.com', '200 Commerce St, Springfield'),
('HealthFirst Distributors', 'Tom Hardy', '555-0203', 'tom@healthfirst.com', '300 Trade Rd, Springfield'),
('Global Meds Ltd.', 'Anna White', '555-0204', 'anna@globalmeds.com', '400 Logistics Ave, Springfield'),
('City Pharma Group', 'Mike Brown', '555-0205', 'mike@citypharma.com', '500 Supply Chain Dr, Springfield');

-- 12. STOCK (references medicine, supplier)
INSERT INTO stock (medicine_id, supplier_id, quantity, expiry_date, last_restocked) VALUES
(1, 1, 500, '2027-06-01', '2026-06-15 10:00:00'),
(2, 2, 300, '2027-03-15', '2026-06-10 11:00:00'),
(3, 3, 400, '2026-12-01', '2026-06-05 09:00:00'),
(4, 4, 1000, '2027-08-20', '2026-06-18 14:00:00'),
(5, 5, 250, '2026-11-30', '2026-06-12 13:00:00');

-- 13. PRESCRIPTION (references doctor, patient)
INSERT INTO prescription (patient_id, doctor_id, tablet, dosage, days, prescription_detail, prescribed_date, status) VALUES
(1, 1, 'Amoxicillin', '500mg twice daily', 7, 'Take after meals for infection', '2026-06-20', 'ACTIVE'),
(2, 5, 'Lisinopril', '10mg once daily', 30, 'For blood pressure control', '2026-06-18', 'ACTIVE'),
(3, 2, 'Ibuprofen', '200mg as needed', 5, 'For fever and pain', '2026-06-22', 'ACTIVE'),
(4, 4, 'Omeprazole', '20mg once daily', 14, 'Before breakfast for acid reflux', '2026-06-19', 'ACTIVE'),
(5, 3, 'Ibuprofen', '400mg twice daily', 10, 'For knee inflammation', '2026-06-21', 'ACTIVE');

-- 14. PATIENT_BILL (references prescription)
INSERT INTO patient_bill (prescription_id, medicine_cost, room_charge, service_charge, test_charge, total_cost, bill_date, status) VALUES
(1, 111.93, 0.00, 200.00, 150.00, 461.93, '2026-06-20', 'UNPAID'),
(2, 675.00, 0.00, 150.00, 0.00, 825.00, '2026-06-19', 'PAID'),
(3, 44.95, 0.00, 100.00, 75.00, 219.95, '2026-06-22', 'UNPAID'),
(4, 199.50, 1500.00, 500.00, 350.00, 2549.50, '2026-06-19', 'UNPAID'),
(5, 89.90, 0.00, 250.00, 200.00, 539.90, '2026-06-21', 'PAID');

-- 15. PAYMENT (references patient_bill)
INSERT INTO payment (bill_id, amount_paid, payment_date, payment_method, status) VALUES
(2, 825.00, '2026-06-19', 'Credit Card', 'COMPLETED'),
(5, 539.90, '2026-06-22', 'Cash', 'COMPLETED'),
(1, 200.00, '2026-06-20', 'Insurance', 'PARTIAL'),
(4, 1000.00, '2026-06-20', 'Debit Card', 'PARTIAL'),
(3, 219.95, '2026-06-23', 'Cash', 'COMPLETED');

-- 16. INSURANCE (references patient)
INSERT INTO insurance (patient_id, policy_number, provider_name, coverage_type, start_date, end_date) VALUES
(1, 'INS-001-A', 'BlueCross', 'Comprehensive', '2026-01-01', '2026-12-31'),
(2, 'INS-002-B', 'Aetna', 'Basic', '2026-03-01', '2026-09-30'),
(3, 'INS-003-C', 'Cigna', 'Dental', '2026-02-15', '2027-02-14'),
(4, 'INS-004-D', 'UnitedHealth', 'Senior Care', '2026-04-01', '2027-03-31'),
(5, 'INS-005-E', 'Humana', 'Comprehensive', '2026-05-01', '2027-04-30');

-- 17. LAB_TEST (independent)
INSERT INTO lab_test (test_name, category, description, price) VALUES
('Complete Blood Count', 'Hematology', 'Full blood panel analysis', 75.00),
('Chest X-Ray', 'Radiology', 'Chest cavity imaging', 150.00),
('Lipid Profile', 'Biochemistry', 'Cholesterol and triglycerides', 90.00),
('Urinalysis', 'Pathology', 'Urine composition analysis', 40.00),
('ECG', 'Cardiology', 'Electrocardiogram', 120.00);

-- 18. TEST_RESULT (references doctor, patient, lab_test)
INSERT INTO test_result (doctor_id, patient_id, test_id, result, reference_range, test_date, notes) VALUES
(1, 1, 1, 'WBC: 7.2, RBC: 4.8, Hgb: 14.2', 'WBC: 4.5-11.0, RBC: 4.2-5.9', '2026-06-21', 'Normal range, no concerns'),
(5, 2, 3, 'Total Chol: 220, LDL: 150, HDL: 45', 'Total: <200, LDL: <100, HDL: >40', '2026-06-19', 'Borderline high cholesterol, recommend diet'),
(2, 3, 4, 'pH: 6.5, Protein: neg, Glucose: neg', 'pH: 4.5-8.0, Protein/Glucose: neg', '2026-06-23', 'Normal urinalysis'),
(4, 4, 5, 'Normal sinus rhythm, rate 72 bpm', 'Normal sinus rhythm 60-100 bpm', '2026-06-20', 'Normal ECG findings'),
(3, 5, 2, 'No acute findings, clear lung fields', 'No abnormalities', '2026-06-22', 'Normal chest X-Ray');

-- 19. MEDICAL_RECORD (references doctor, patient)
INSERT INTO medical_record (patient_id, doctor_id, visit_date, symptoms, diagnosis, treatment, notes) VALUES
(1, 1, '2026-06-20', 'Chest tightness, shortness of breath', 'Mild angina suspected', 'Prescribed nitroglycerin, scheduled stress test', 'Follow up in 2 weeks'),
(2, 5, '2026-06-18', 'Fatigue, frequent urination, blurred vision', 'Type 2 Diabetes', 'Prescribed Metformin, dietary consultation', 'Monitor blood glucose weekly'),
(3, 2, '2026-06-22', 'Fever 102F, sore throat, cough', 'Acute pharyngitis', 'Prescribed Amoxicillin, rest and fluids', 'Return if fever persists beyond 48h'),
(4, 4, '2026-06-19', 'Short-term memory loss, confusion', 'Early onset dementia evaluation', 'Referred for MRI and cognitive therapy', 'Neurology follow-up scheduled'),
(5, 3, '2026-06-21', 'Right knee swelling, pain on movement', 'Grade 2 knee sprain', 'RICE protocol, prescribed Ibuprofen', 'Avoid weight-bearing for 1 week');

-- 20. STAFF (references department, role)
INSERT INTO staff (full_name, email, phone, salary, hire_date, dept_id, role_id) VALUES
('Amy Johnson', 'amy.johnson@hms.com', '555-0301', 55000.00, '2024-03-15', 5, 3),
('Claire Davis', 'claire.davis@hms.com', '555-0302', 35000.00, '2024-06-01', 5, 4),
('Paul Green', 'paul.green@hms.com', '555-0303', 48000.00, '2025-01-10', 1, 3),
('Rachel Brown', 'rachel.brown@hms.com', '555-0304', 52000.00, '2025-02-20', 4, 3),
('Steve Miller', 'steve.miller@hms.com', '555-0305', 42000.00, '2025-09-01', 2, 4);
