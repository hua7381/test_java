package zgh.designPattern.observer;

public interface Subject {
	public void registerObserver(Observer obs);
	public void removeObserver(Observer obs);
	public void notifyObservers();
}