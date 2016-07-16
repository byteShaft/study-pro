package in.technicus.studypro.Services;

/**
 * Created by shubham on 19/6/16.
 */
public class ListPojo {
    String agendaTitle,agendaImportance, agendaId;
    public ListPojo(){
        agendaTitle ="";
        agendaImportance = "";
    }

    public ListPojo(String agendaTitle, String agendaImportance){
        this.agendaTitle = agendaTitle;
        this.agendaImportance = agendaImportance;
    }

    public ListPojo(String agendaTitle, String agendaImportance, String agendaId){
        this.agendaTitle = agendaTitle;
        this.agendaImportance = agendaImportance;
        this.agendaId = agendaId;
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
    public void setAgendaId(String agendaId) {
        this.agendaId = agendaId;
    }

    public String getAgendaId() {
        return this.agendaId;
    }
    public String getAgendaImportance(){
        return this.agendaImportance;
    }
}
