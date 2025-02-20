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


INSERT INTO projects (user_id, name, description)
VALUES (1, 'Ceramic Vase', 'A handmade ceramic vase with intricate patterns.');

INSERT INTO images (project_id, image_url)
VALUES (1, 'https://example.com/images/project1.jpg');
