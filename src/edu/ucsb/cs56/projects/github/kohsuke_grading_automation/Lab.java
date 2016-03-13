package edu.ucsb.cs56.projects.github.kohsuke_grading_automation;

import org.kohsuke.github.*;
import java.util.*;
import org.apache.commons.io.IOUtils;
import java.nio.charset.Charset;

import java.nio.file.StandardOpenOption;
import java.nio.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * An abstract class of which Lab00 - Lab03 inherit from.
 * Consider renaming to LabUCSB, since all labs hosted on github.ucsb.edu inherit from this lab.
 * Could be a good idea to have Lab be an abstract class, and then LabUCSB and LabGit as two other abstract
 * classes inheriting from Lab, then have regular classes Lab00 - Lab10 inherit from either LabUCSB or LabGit.
 *
 * Currently, this class initializes some member variables and has an abstract method grade(), which is delegated
 * to subclasses to implement.
 * @author Jonathan Easterman
 * @version 1.0
 */


public abstract class Lab {
  /** me refers to the GHMyself instance of whoever enters their credentials on instantiation
      of a subclass of Lab */
  protected GHMyself me;

  /** github refers to the GitHub instance, which is either github.com or github.ucsb.edu */
  protected GitHub github;

  /** repos refers to a list of GHRepository. */
  protected List<GHRepository> repos;

  /** names is a list of github IDs of CS 56 students. Can either be github.com or github.ucsb.edu IDs*/
  protected List<String> names;

  /** rosterName is the path from the root of the repository to a CSV file containing student github IDs */
  protected String rosterName = "roster.csv";

  /** username and password are the username and password for github.com or github.ucsb.edu */
  protected String username, password;

  /** admins is a set of github usernames of people with admin access to CS 56 organization */
  protected Set<String> admins = new HashSet(Arrays.asList(
    "jrcryan",
    "grinta",
    "jdogg5566",
    "pconrad",
    "arda",
    "chall01",
    "vincenicoara",
    "allisonshedden",
    "omeedrabani",
    "hanna",
    "mbahia"
  ));


  // Base class constructor that retrieves username and password
  // Consider replacing password with asterisks in stdout while user is typing 
  Lab() {
  /**
   * Constructor initializes me object through calling initGitHub with username and password
   * initGitHub is abstract because implementation will differ depending on whether
   * program is connecting with github.com or github.ucsb.edu
   */
    try {
      Scanner sc = new Scanner(System.in);
      System.out.println("Please enter your GitHub UCSB username: ");
      username = sc.nextLine();
      System.out.println("Please enter your GitHub UCSB password: ");
      password = sc.nextLine();
      initGitHub(username, password);
      me = github.getMyself();
      initRepos();
      initNames();
    } catch(IOException e) {
      e.printStackTrace();
    }
  }

  protected abstract void initGitHub(String username, String password);

  protected abstract void initRepos();

  protected void initNames() {
  /**
   * Initializes list of names of students in CS 56 by reading from file
   * specified by path with member variable rosterName
   */
    names = new ArrayList<String>();
    BufferedReader br = null;
    String line = "";
    try {

      br = new BufferedReader(new FileReader(rosterName));
      int i = 0;
      while ((line = br.readLine()) != null) {
        if (i != 0){
          String[] columns = line.split(",");
          names.add(columns[5]);
        }
        i++;
      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (br != null) {
        try {
          br.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

  }

  public abstract void grade();


}
