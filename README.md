# PLACES
IMA17 Project - SWENGA - Webdevelopement

Places is a web application where users can plan trips by browsing recommendations of other users. It features a variety of different locations and trip destinations where they will be able to share their experiences and photos of a particular destination and share that information with other users. Places was developed during the study course Software Engineering Advanced at FH JOANNEUM (UAS) in the Bachelor's degree Informationsmanagement (IMA17). The project was supported by DI (FH) Johann Blauensteiner and DI (FH) Stefan Krausler-Baumann.

## Setup Instructions
1. [Download](https://github.com/xxx) project source
2. Create ``New dynamic Web project`` Eclipse project
3. Convert it to a Maven project and import the sources
4. Setup ``db.properties`` in folder ``src`` with your database information
5. Setup your Eclipse project (JRE, Server, and Targeted Runtime)
6. Publish project to Tomcat and deploy Tomcat (9.0)
7. [*click*](http://localhost:8080/Places/)
8. First startup takes a little bit longer since the Database is initialized.
9. Login credentials: admin/password, user/password. Visitors can see some information by clicking on the visitor button.

Team Members:
  * David Becker: @BeckerDavid
  * Viktoria Gradwohl: @Accalia44
  * Michael Derler: @YellowIcicle
  * Ardian Qerimi: @ardianq
  
Workload distribution:
  * David Becker: Recommendation (Backend), Like-Implementation, Dashboard (Frontend +Backend)
  * Viktoria Gradwohl: Login (Backend), Visitor, Search-Recommendation (Queries), Password Reset and Change 
  * Michael Derler: Design, Login-Register (CSS + HTML), AdminPanel (Backend+Frontend), Sitebar
  * Ardian Qerimi: Login-Register (Backend), Profilepicture + RecPicture Implementation, Journey  
