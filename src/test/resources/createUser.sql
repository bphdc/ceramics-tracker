INSERT INTO users (username, email, password_hash, bio, profile_picture, role, created_at)
VALUES (
           'testuser',
           'testuser@example.com',
           'passwordhash12314',  -- Example bcrypt hash, replace with a real hash
           'This is a test user.',
           'profile_pic.jpg',
           'user',
           NOW()
       );