package job.calendar.functions;

import java.time.LocalDate;

public class DetailsPane {
    private LocalDate date;

    public void setDetails(LocalDate date){
        this.date = date;
    }

    public LocalDate getDate(){
        return date;
    }

}
