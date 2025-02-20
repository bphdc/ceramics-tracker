-- create admin user

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

-- create glaze

INSERT INTO glazes (name, description, type)
VALUES ('Test Glaze', 'A glossy blue glaze', 'Overglaze');

-- create tag

INSERT INTO tags (name)
VALUES ('test tag');


-- create project, user must exist first

INSERT INTO projects (user_id, name, description)
VALUES (1, 'Ceramic Vase', 'A handmade ceramic vase with intricate patterns.');

-- create image, project must exist first

INSERT INTO images (project_id, image_url)
VALUES (1, 'https://example.com/images/project1.jpg');

-- create project entry, project must exist first

INSERT INTO project_entries (project_id, entry_text)
VALUES (1, 'Test entry');


-- create project glaze link, project and glaze must exist first
INSERT INTO project_glazes (project_id, glaze_id)
VALUES (1, 1);


-- create project tag link, project and tag must exist first
INSERT INTO project_tags (project_id, tag_id)
VALUES (1, 1);

-- create admin action
INSERT INTO admin_actions (admin_id, action_type, target_id)
VALUES (1, 'Add Glaze', 1);
