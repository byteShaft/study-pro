package in.technicus.studypro.Services;

/**
 * Created by shubham on 19/6/16.
 */
public class ListPojo {
    String agendaTitle,agendaImportance;
    public ListPojo(){
        agendaTitle ="";
        agendaImportance = "";
    }
    public ListPojo(String agendaTitle, String agendaImportance){
        this.agendaTitle = agendaTitle;
        this.agendaImportance = agendaImportance;
    }
    public void setAgendaTitle(String agendaTitle){
        this.agendaTitle = agendaTitle;
    }
    public String getAgendaTitle(){
        return this.agendaTitle;
    }
    public void setAgendaImportance(String agendaImportance){
        this.agendaImportance = agendaImportance;
    }
    public String getAgendaImportance(){
        return this.agendaImportance;
    }
}
