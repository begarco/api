package ovh.garcon.api

class Contributor {

    String username
    String firstname
    String lastname
    String contact
    String img
    String[] url

    static hasMany = [files: File, projects: Project]
    static belongsTo = [Project, File]

    static constraints = {
        files nullable: true
        projects nullable: true
    }
}
