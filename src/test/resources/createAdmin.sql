INSERT INTO users (username, email, password_hash, bio, profile_picture, role, created_at)
VALUES (
           'testAdmin',
           'testAdmin@example.com',
           'passwordhash12314',  -- Example bcrypt hash, replace with a real hash
           'This is an admin user.',
           'profile_pic_admin.jpg',
           'admin',
           NOW()
       );