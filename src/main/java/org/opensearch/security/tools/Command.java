package org.opensearch.security.tools;

public abstract class Command {
    public abstract int execute(final String[] args) throws Exception;

    public abstract String describe();

    public abstract void usage();

    public void main(final String[] args) throws Exception {
        try{
            final int returnCode = execute(args);
            System.exit(returnCode);
        } catch (Throwable e) {
            System.out.println("Unexpected error");
            System.exit(-1);
        }
    }
}
