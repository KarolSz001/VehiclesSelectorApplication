"# VehiclesSelectorApplication" 
## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Features](#features)
* [Status](#status)

## General info
Multi-Module Maven Application with Java Modules , still in progress adding new functionality

## Technologies
* Java - version 12
* gson - version 2.8.4
* maven - version 3.6
* Multi-Module Maven 
* TestUnit junit 4.12
* junit-jupiter

## Setup
download, compile and run, in module main file to compile main-1.0-SNAPSHOT-jar-with-dependencies.jar

## Code Examples
Map<String, Car> map = cars.stream()
                .collect(Collectors.groupingBy(c -> c.getModel()))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        m -> m.getKey(),
                        m -> m.getValue().stream()
                                .sorted(Comparator.comparing(s -> s.getPrice(), Comparator.reverseOrder()))
                                .findFirst()
                                .orElseThrow(() -> new IllegalStateException(" CAN'T found ")
                                )));

## Features

To-do list:
- cleanCode - optimize code 
- Junit
- Json



## Status
Project is: _in_progress_"# VehiclesSelectorApplication" 
