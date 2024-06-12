import java.time.LocalDate;

public class DiaryEntry {
    private LocalDate date;
    private String title;
    private String content;
    private String weather; 

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
        return "날짜: " + date + "\n제목: " + title + "\n한 줄 일기: " + content + "\n날씨: " + weather;
    }
}
