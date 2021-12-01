package common;

public interface ListFeed {
    public String next();
    public boolean hasNext();
    public void close();
}
