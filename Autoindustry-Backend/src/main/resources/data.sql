-- Dummy users (passwords BCrypt encoded for 'pass123')
INSERT INTO users (username, password, role, email) VALUES
                                                        ('carowner', '$2a$10$K.RVCI7rE4t/VdP2BCgK3O9S3uJn9jJ7yYp4z8tKxW5z3fBn3GqG.', 'CAR_OWNER', 'ENC(carowner@example.com)'),
                                                        ('repairshop', '$2a$10$K.RVCI7rE4t/VdP2BCgK3O9S3uJn9jJ7yYp4z8tKxW5z3fBn3GqG.', 'REPAIR_SHOP', 'ENC(repairshop@example.com)'),
                                                        ('vendor', '$2a$10$K.RVCI7rE4t/VdP2BCgK3O9S3uJn9jJ7yYp4z8tKxW5z3fBn3GqG.', 'VENDOR', 'ENC(vendor@example.com)');