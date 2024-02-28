package observer02;

import java.util.ArrayList;
import java.util.List;

/**
 * @author agj017@gmail.com
 * @since 2022/03/29
 */
public class NewsStation implements Subject {

    List<Observer> observers;

    String title;

    String contents;

    boolean isSending;

    public NewsStation() {
        this.observers = new ArrayList<>();
        this.isSending = false;
    }

    public void breakNews(String title, String contents) {
        this.title = title;
        this.contents = contents;
        this.notifyObservers();

    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        if(!isSending) {
            isSending = true;
            for(Observer observer: observers){
                observer.update(this);
            }
            isSending = false;
        }

    }
}
