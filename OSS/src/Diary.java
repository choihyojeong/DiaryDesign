import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Diary {
    private List<DiaryEntry> entries;

    public Diary() {
        entries = new ArrayList<>();
    }

    public void addEntry(DiaryEntry entry) {
        entries.add(entry);
    }

    public void updateEntry(LocalDate date, String title, String content, String weather) {
        for (DiaryEntry entry : entries) {
            if (entry.getDate().equals(date)) {
                entry.setTitle(title);
                entry.setContent(content);
                entry.setWeather(weather);
                return;
            }
        }
    }

    public void removeEntry(LocalDate date) {
        entries.removeIf(entry -> entry.getDate().equals(date));
    }

    public DiaryEntry getEntry(LocalDate date) {
        for (DiaryEntry entry : entries) {
            if (entry.getDate().equals(date)) {
                return entry;
            }
        }
        return null;
    }

    public List<DiaryEntry> getAllEntries() {
        return entries;
    }
}
