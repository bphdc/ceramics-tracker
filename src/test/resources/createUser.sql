INSERT INTO users (username, email, name, bio, profile_picture, role, created_at)
VALUES (
           'testuser',
           'testuser@example.com',
           'This is a test user.',
           'profile_pic.jpg',
           'user',
           NOW()
       );