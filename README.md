# cs56-github-kohsuke-grading-automation

A program that builds an abstraction layer on top of the Kohsuke GitHub library to facilitate the automation of grading for CMPSC 56, a lower division course at UCSB.

Authors:

1. Jonathan Easterman

## Kohsuke GitHub API
http://github-api.kohsuke.org/

Who? - Kohsuke Kawaguchi --> https://github.com/kohsuke

What? - A Java library that offers an object oriented approach to interacting with GitHub's API. Allows programmers to create applications that make API calls to GitHub.com and GitHub enterprise (e.g. github.ucsb.edu), without having to deal with the nitty gritty of HTTP requests. 

When? - Currently in development, current release (as of March 2016) is 1.73. This repo currently includes version 1.73 in the lib folder.

Where? - Check out the official website for examples, important info, and a link to the javadoc --> http://github-api.kohsuke.org/

Why? -- Because, why build a GitHub API from scratch when we can build on top of an already existing, robust library. Seriously, this library is great and fairly easy to use. Once you spend maybe an hour or two reading the javadoc and uncovering the relationships between certain objects, you will realize the potential of the Kohsuke API.

## Current status of this Repo (March 2016)
This repo currently employs a basic object-oriented style. There is an abstract class called Lab that all Labs hosted on github.ucsb.edu inherit from. There are three classes named Lab00, Lab02, Lab03 that are sub-classes of Lab. There is a class called GitHubUCSB that contains a main method for instantiating any one of the 3 above Labs, depending on user input. Finally, there is a class called GitHubAPI, which interacts with github.com and basically showcases the variety of objects defined in the Kohsuke API. It defines a bunch of methods for getting information about issues within a repo, and then also a main method that calls these methods.

## Suggestions for future authors
* Rename Lab to LabUCSB
** Create a new abstract class with name Lab, and a new abstract class with name LabGit
** Have LabUCSB and LabGit both inherit from Lab, then labs hosted on github.ucsb.edu would inherit from LabUCSB (i.e. 0 - 3), and labs hosted on github.com would inherit from LabGit
* Create a class for grading Lab01
* Make code in Lab00, Lab02, Lab03 DRYer. There seems to be a lot of repetition in those classes that could be abstracted out to a method in LabUCSB
* Incorporate Bash scripting to programmatically run tests on labs.
** i.e. use Java code to compile a list of addresses for the repo that can be used as input to git clone <repo_address_here>
** Pipe that list into a bash script that calls git clone on each repo name, runs a test suite, reports % of tests passed, then deletes the repo when finished

## Questions

If you have any questions please feel free to email me at jonathaneasterman@umail.ucsb.edu

