# SC2002-CAM-App-Project

## Introduction
This is a project for the NTU module SC2002 Object Oriented Design & Programming for 2023 S1. 

It involves creating a Camp Application and Management System (CAMs) through the use of Object Oriented Programming conforming to the SOLID design principles.

Documentation of the code can be found in the [Javadoc folder](https://github.com/weihonglwh/SC2002-CAM-App-Project/tree/main/Javadoc).
Open the `index.html` file after downloading to view an interactive Javadoc HTML webpage.

An [UML class diagram](https://github.com/weihonglwh/SC2002-CAM-App-Project/blob/main/class-diagram-final.jpg) is available for reference.
 
The [SC2002 Assignment 2023S1 PDF file](https://github.com/weihonglwh/SC2002-CAM-App-Project/blob/main/SC2002%20Assignment%202023S1.pdf) consists of the full requirements for the project.

Java is the language of choice for this project.

## Preconditions
Before using the application, note that 5 `.csv` files are required to be in the same working directory as your `.class` files after compilation. See [Usage](#Usage) for details of compilation.

It is recommended to not tamper with the files manually with the exception of `student.csv` and `staff.csv` to create new accounts.

Refer to the table for the required files:
| File | Description | Required Entries |
| - | - | - |
| camp.csv | This file consists of all camps in the system. It is recommended to not tamper with the file manually. | Only the header is required.|
| enquiry.csv | This file consists of all enquiries for camps in the system.  | Only the header is required.|
| student.csv | This file consists of all student accounts in the system. | **Minimally 1 account is required alongside the header.**|
| staff.csv | This file consists of all staff accounts in the system. | **Minimally 1 account is required alongside the header.**|
| suggestion.csv | This file consists of all suggestions for camps in the system. | Only the header is required.|

## Usage
Please see [preconditions](#Preconditions) prior to running the application to avoid issues.

A JDK is required to compile the source files.

Upon compilation, navigate into the folder containing the `.class` files and execute the `java CAMApp` command to run the program.
