# Clean Water Crowdsourcing
## General
Our project this semester supports the Georgia Tech Serve/Learn/Sustain program. More details on this program are at: external link: http://serve-learn-sustain.gatech.edu/welcome . It is loosely based on the NASA challenge here: external link: https://2015.spaceappschallenge.org/challenge/clean-water-mapping/

Specifically, in our class, we will create an application that supports finding clean drinking water and reporting on testing results. In many parts of the world, access to clean water is a severe problem. Many times, water is available, but people do not know where to get it. Other times, water may appear clean, but has unacceptable levels of contaminants.

Even in areas where there is a lack of clean water, the population has access to devices on which applications can run. Most of these devices are smart phones, however since this is our JavaFX semester, we will create a Java application to provide the functionality.

Our application will allow water reporting to crowd sourced. People can report on locations where water is available. Users of the application can find the nearest water source or report on a new water source. Workers with test kits will also be able to report on contaminant levels. The application will provide historical graphs to show variations in water quality over time for a specific location.

A normal application like this would be networked and have a database backend. For this class, those skills are not pre-requisites, so our application will be simulated multi-user. We will assume all the data will fit into memory, so that you may store data in an appropriate basic data structure. We will persist (save the data) to disk. Making an actual networked application with a database is extra credit.

FOR THIS SEMESTER WE WILL DESIGN THE ENTIRE APPLICATION, HOWEVER WE WILL IMPLEMENT ONLY WHAT IS IN THE MILESTONES. You may implement other features and receive extra credit.

## Authorization Levels
## User
The user has the most basic rights in the system. They may submit a report on water availability and they may view available water sources.

## Worker
Workers may do everything a User can. In addition, they can report on water purity levels.

## Manager
Managers can do everything a Worker can do. Additionally, they can view historical reports and trends of water purity. They can also delete individual reports that they deem inaccurate.

## Administrator
Administrator accounts are special accounts for maintenance of the system. Admins can delete any accounts, ban a user from submitting reports (although that user can still view water sources). They can also unblock an account that has been locked for incorrect login attempts. The admin can view the security log.

## Security
## Login
All authorization levels must login. Login consists of a username and a password. After successful login, the individual will be authorized to accomplish the appropriate tasks. After 3 incorrect login attempts, the individual's account is locked and cannot be used until an admin unlocks it.

## Logging
A security log is maintained for the system. The security log keeps track of the following actions:

1. Login attempt. Data is timestamp, userid and success status (unknown id, bad password, success)
2. Account delete. Data is timestamp, userid of admin, userid of deleted account
3. User ban. Data is timestamp, userid of admin, userid of banned account
4. Unblock account. Data is timestamp, userid of admin, userid of unblocked account
5. Report delete. Data is timestamp, userid of manager, report number

## Report Data
## Water source report
A user can submit this report any time they are logged in. This report consists of:

1. Date and time of report (can be autogenerated by application)
2. Report Number (must be autogenerated by application)
3. Name of reporter (can be autogenerated from user information)
4. Location of water (manually entered. using any kind of location services or gps is extra credit).
5. Type of water (Bottled, Well, Stream, Lake, Spring, Other)
6. Condition of water (Waste, Treatable-Clear, Treatable-Muddy, Potable)
## Water availability
This report is just a google map display with a pin in each location that water has been reported. Clicking on a pin will give you the Type and Condition of the water (for example "Bottled / Potable" or "Stream / Treatable-Clear".

## Water Purity Report
A real water report would need to address multiple bacteria/virus and chemical contaminants. For our basic application, we assume that parts-per-million (PPM) is our measure and we will lump everything into two categories: Viruses and Contaminants.

1. Date and time of the report (can be autogenerated by application)
2. Report Number (must be autogenerated by application)
3. Name of Worker (can be autogenerated from worker information)
4. Location of water (manually entered. using any kind of location services or gps is extra credit).
5. Overall Condition (Safe / Treatable / Unsafe)
6. Virus PPM
7. Contaminant PPM
## Historical Report
The manager should enter a location, either virus or contaminant, and a year. The system will then display an XY graph where the X axis is the month and the Y axis is the PPM. Each month's data point can be an average of the reports for that month if there are more than one.

