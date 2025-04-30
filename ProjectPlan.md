# Project Plan

### Weeks Prior to Week 4
- [X] Create project repository on GitHub
- [X] Create project structure in intellij and push
- [X] Add link to list of indie projects in student repo.
- [X] Complete Problem Statement
- [X] Weekly reflection/time log
- [X] Research possible Web Services/APIs to use
- [X] List technologies, versions and how they will be used
- [X] Write project plan
- [X] Document user stories and select MVP stories
- [X] Confirm MVP stories meet Ent Java indie project objectives
- [X] Design screens - make sure all MVP user stories are covered
- [X] Triple-check for Checkpoint 1
- [X] Update journal/time log/reflection

For those using this as a model, continue adding weekly tasks by considering:
1. which user story will you work on in a given week
2. detail the tasks needed to complete that user story
3. what we are doing in class each week and what you might add to your project as a result, either in that week or the following week
4. add in project checkpoints/milestones and tasks to achieve them
5. I started a few of these below as examples

### Weeks 4-7 
#### Just trying to get through all of this work...
- [X] First cut at database design
- [X] Create the dev version of the database
- [X] Create test version of database for unit testing
- [ ] ~~Figure out what jsps i need and stub them out~~ decided to just do this as i get to it
- [X] Figure out what persistence classes I need and stub them out
- [X] Figure out what controller classes I need and stub them out
- [X] Figure out what entities i need and stub them out
- [X] Create the config files for the DB connection info (dev and test)
- [X] Create unit tests for at least one class
- [X] Create a class for the DB connection business
- [X] Update weekly reflections
- [X] Add logging
- [X] Make sure I hit everthing I need for the 2nd checkpoint

### Week 7 (check point 2 so do that too)
#### User Sign Up (MVP)
- [] ~~Design Sign Up JSP~~ (aws)
- [X] Implement Sign Up form (name, email, password)
- [X] Implement AWS Cognito sign-up functionality
- [ ] ~~Validate Sign Up form~~ (aws)
- [X] Test user creation in AWS Cognito
- [ ] ~~Write unit tests for Sign Up functionality~~ not needed bc it's through aws
- [X] Integrate with backend services (AWS Cognito)

#### User Sign In (MVP)
- [ ] ~~Design Sign In JSP~~ (aws)
- [ ] ~~Implement Sign In form (email, password)~~ (aws)
- [X] Implement AWS Cognito sign-in functionality
- [ ] ~~Validate Sign In form~~ (aws)
- [X] Test user login via AWS Cognito
- [ ] ~~Write unit tests for Sign In functionality~~ (aws)
 
^^ most of this doesn't apply as it's via Cognito

#### User Create New Project (MVP)
- [X] Design Create Project JSP
- [X] Implement form to add new project (title, description, etc.)
- [X] Implement backend logic for creating a new project
- [X] Persist project data in the database
- [X] Test project creation functionality 
- [X] Write unit tests for Create Project functionality (dao test)

### Week 8
#### User Edit Profile
- [X] Design Edit Profile JSP
- [X] Implement form to update user profile (email, name, etc.)
- [X] Implement backend logic for updating user profile
- [X] Validate profile edit form
- [X] Test profile edit functionality
- [X] Write unit tests for Edit Profile functionality (dao test)

#### User Delete Profile
- [X] Implement delete user profile functionality in the backend
- [X] Design confirmation JSP for profile deletion
- [X] Integrate with AWS Cognito to delete user account
- [X] Test profile deletion functionality
- [X] Write unit tests for Delete Profile functionality

#### User Edit Project
- [X] Design Edit Project JSP
- [X] Implement form to edit project details
- [X] Implement backend logic for updating project details
- [X] Test project edit functionality
- [X] Write unit tests for Edit Project functionality

#### User Delete Project
- [X] Implement delete project functionality in the backend
- [X] Design confirmation JSP for project deletion
- [X] Test project deletion functionality
- [X] Write unit tests for Delete Project functionality

### Week 9 (Checkpoint 3 so do chkpt 3 too)
#### User Add Picture (MVP)
- [X] Design Add Picture JSP (file upload form)
- [X] Implement image upload functionality (support for multiple images)
- [X] Integrate file upload with the database
- [X] Implement image display in the project details
- [X] Test image upload and display functionality
- [X] Write unit tests for Add Picture functionality (dao)

#### User View Projects
- [X] Design View Projects JSP
- [X] Implement functionality to display list of projects
- [X] Implement pagination and sorting for projects
- [X] Test project display functionality
- [X] Write unit tests for View Projects  (dao)


DECIDED TO REMOVE ABILITY TO LET USER ADD GLAZE AND TAGS TO DB. KEEP IT ADMIN FOR NOW FOR SIMPLICITY


#### User View Glazes
- [ ] ~~Design View Glazes JSP~~ users will see glazes from dropdown list in prj
- [X] Implement functionality
- [X] ~~Implement search for glazes~~
- [X] Test glaze view functionality
- [X] Write unit tests for View Glazes functionality

#### User Add Existing Tags to Project
- [X] Design Add Tags JSP
- [X] Implement form to select and add tags to projects
- [X] Implement backend logic to associate tags with projects
- [X] Test tag addition functionality
- [X] Write unit tests for Add Tags functionality

#### User Search Projects by Tag
- [X] Implement tag-based search functionality
- [X] Update Search Projects JSP to support filtering by tags
- [X] Test tag search functionality
- [X] Write unit tests for Search by Tag functionality

#### User Search Projects by Glaze
- [X] Implement glaze-based search functionality
- [X] Update Search Projects JSP to support filtering by glaze
- [X] Test glaze search functionality
- [X] Write unit tests for Search by Glaze functionality

### Week 10
#### Admin Add Glaze
- [X] Design Add Glaze JSP
- [X] Implement form to add new glaze (name, description)
- [X] Persist glaze data in the database
- [X] Test glaze creation functionality
- [X] Write unit tests for Add Glaze functionality


#### Admin Edit Glazes
- [X] Design Admin Edit Glaze JSP
- [X] Implement functionality to update glaze details
- [X] Test Admin Edit Glaze functionality
- [X] Write unit tests for Admin Edit Glazes functionality

#### Admin Delete Glazes
- [X] Design Admin Delete Glaze JSP
- [X] Implement functionality to delete glaze from library
- [X] Test Admin Delete Glaze functionality
- [X] Write unit tests for Admin Delete Glazes functionality

#### Admin Add New Tags
- [X] Design Admin Add Tag JSP
- [X] Implement functionality to add new tags to library
- [X] Test Admin Add Tag functionality
- [X] Write unit tests for Admin Add New Tags functionality

### Week 11
#### Admin Edit Tags
- [X] Design Admin Edit Tag JSP
- [X] Implement functionality to update tags
- [X] Test Admin Edit Tag functionality
- [X] Write unit tests for Admin Edit Tags functionality

#### Admin Delete Tags
- [X] Design Admin Delete Tag JSP
- [X Implement functionality to delete tags from library
- [X] Test Admin Delete Tag functionality
- [X] Write unit tests for Admin Delete Tags functionality

### Week 12
#### ~~User Calculate Shrinkage (MVP)~~ Changed this to Hive AI image search
- [X] Hive JSP
- [X] Implement logic (based on user input)
- [X] Validate form (error handling in client and servlet)
- [X] Test external api
- [X] Write unit tests for hiveai functionality

### Week 13-14
#### Testing and Refinement
- [ ] Perform end-to-end testing (user stories, CRUD operations)
- [ ] Refactor code based on test results
- [ ] Conduct code reviews and optimize performance
- [ ] Ensure security practices are implemented (password hashing, input validation)

### Week 15
#### Implement Feedback from Week 14 Review
- [ ] Address any bugs or improvements from the review
- [ ] Update documentation with new features and changes
- [ ] Explore data val using hibernate validator
- [ ] Final Testing

### Week 16
#### Final Touches
- [ ] Finalize codebase (clean up, document changes)
- [ ] Prepare and rehearse final presentation
- [ ] Create and upload final video for project walkthrough
- [ ] Add final project link to the indie project list in the student repo
