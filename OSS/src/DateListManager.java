import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DateListManager {
    private List<LocalDate> dateList;

    public DateListManager() {
        dateList = new ArrayList<>();
    }

    public void addDate(LocalDate date) {
        if (!dateList.contains(date)) {
            dateList.add(date);
        }
    }

    public void removeDate(LocalDate date) {
        dateList.remove(date);
    }

    public List<LocalDate> getAllDates() {
        return dateList;
    }
}
