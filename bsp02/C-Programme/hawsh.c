//getenv, setenv, chdir, getcwd, strcmp, strlen, fork, waitpid, execlp, exit
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <limits.h>
#include <errno.h>

//Globale Variablen
void type_prompt();
void read_command();
char *command;



int main() {  
  pid_t PIDstatus;
  int status;
  char buf[20];  
 

  while (1) { /* Endlosschleife */
    type_prompt(); /* Prompt ausgeben */
    read_command(); /* Eingabezeile von Tastatur lesen */
    if ((strcmp(command,"Quit") == 0)) {exit(0);}
    if ((strcmp(command,"version") == 0)) {printf("Autor: Beitz_Rofler, Version 0.0\n");continue;}
    if ((strcmp(&command[0],"/") == 0)) {chdir(command);continue;}
    if ((strcmp(command,"Help") == 0)) {printf("Kurzbefehle:\nQuit: Beenden der HAW-Shell\nversion: Anzeige des Autors und der Versionsnummer der HAW-Shell\n/[Pfadname] Wechsel des aktuellen Arbeitsverzeichnisses (analog zu cd). Es muss immer ein kompletter Pfadname eingegeben werden.\nHelp: Anzeige der möglichen Built-In-Befehle mit Kurzbeschreibung.\n");continue;}
    //if ((strcmp(&command[sizeof(command)-2],"&") == 0)) {chdir(command);continue;}
    PIDstatus = fork(); /* Kind erzeugen */
    
    if (PIDstatus < 0) {
      printf("Unable to fork\n"); /* Fehlerbedingung */
      continue; /* Schleife wiederholen */
    }
    if (PIDstatus > 0) {
      waitpid (PIDstatus, &status, 0); /* Elternprozess wartet auf Kind */
    } else {
       execve(getenv("mycommand"), NULL, NULL); /* Das Kind-Programm ausführen */
      if (status == -1) { 
        perror(""); 
        exit(1);
      }
      exit(0);
    }
  }
}

void type_prompt(void)
{
 
  char cwd[PATH_MAX];
  getcwd(cwd, sizeof(cwd));
  char * user = getlogin();
  printf("@%s, what do you want to do here at %s?", user, cwd);
  fflush(stdout);
}

void read_command()
{
  char tmp[256];
  fgets(tmp, sizeof(tmp), stdin);
  fflush(stdin);
  tmp[strlen(tmp)-1] = '\0';
  command = strtok(tmp, " ");
  setenv("mycommand", command, 1);
 
  
}  
 