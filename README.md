MMAD-MUSIC 2.0 IS IN PROGRESS:
CURRENTLY WORKING ON DEVELOPING THE APPLICATION AS A WEB SERVICE


Local Database Setup & Configuration

In order to store user data in MMAD Music you will need to configure a local MySQL instance. The following steps will show you how to get it up and running.
1.	Install MySQL Workbench
   
       https://dev.mysql.com/downloads/workbench/ 

    Follow the instructions in the link above to install MySQL Workbench


Use the configuration options shown in the below screenshots when installing.
![image](https://github.com/user-attachments/assets/c119c621-81c7-44cf-93aa-b9f2fee17d90)
![image](https://github.com/user-attachments/assets/035db3d2-42df-4ca9-b4e7-3984887b4ffa)
![image](https://github.com/user-attachments/assets/854a510f-d03d-43ad-8561-062a52531ffb)
![image](https://github.com/user-attachments/assets/92377c37-37d8-41eb-b82d-80af80d353dd)
![image](https://github.com/user-attachments/assets/ef246990-081a-43b6-95aa-d365cae12767)
![image](https://github.com/user-attachments/assets/d2f98110-abed-41cd-b6cd-a62d0e240c98)

 
 
 
 
 


2.	Configure Localhost MySQL Connection
   
      You will be prompted to set a password for your local MySQL connection. Whatever password you choose for this will have to be pasted into the fourth line of the “SQLCredentials.txt” file. There are two versions of this file that need to be changed. The first is in the MusicShare\src\main\resources directory, and the second is located in MusicShare\src\test\resources.

 ![image](https://github.com/user-attachments/assets/16f417a4-f778-4e38-9634-ef5dfe1bc686)


You will also be prompted to create a default schema. Make the name of this “music_share_db”.




3.	Execute SQL statement to setup data tables
   
       Open your newly configured Local connection. In the SQL Editor, open the SQL file "MMAD.sql" which is located in the "Other resources" folder. Click the lightning bolt symbol to execute the statement. This should successfully create the tables in your music_share_db schema.

4.locate the directory where the pom.xml file is located run "mvn clean install" command in the terminal

Once the Database is up and running, you can navigate to the main.java (MusicShare/src/main/java/MMAD) and run the program.



 
