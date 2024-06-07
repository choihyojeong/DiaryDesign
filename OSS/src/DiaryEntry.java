import java.time.LocalDate;

public class DiaryEntry {
    private LocalDate date;
    private String title;
    private String content;
    private String weather;  // Weather 정보를 포함할 수 있습니다.

    public DiaryEntry(LocalDate date, String title, String content, String weather) {
        this.date = date;
        this.title = title;
        this.content = content;
        this.weather = weather;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        return "Date: " + date + "\nTitle: " + title + "\nContent: " + content + "\nWeather: " + weather;
    }
}
