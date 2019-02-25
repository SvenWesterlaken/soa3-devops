package soa.svenwstrl.scm;

interface VersionControlConnection {

    //factory method pattern
    //connect with version control tool to pull and push code for example
    fun createConnection(): VersionControlSystem

    //example implementation:
   /* if ("Github")
    {
        vcs = new Github(repository);
    }*/

}
