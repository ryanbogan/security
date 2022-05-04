package org.opensearch.security.tools;

import java.util.*;

public class OpenSearchAdmin {
    protected static final Map<String, Command> commands = new LinkedHashMap<>();
    static Set<String> helpOptions = new HashSet<>(Arrays.asList("--help", "-h", "help"));

    static {
        System.out.println("A tool for installing and configuring OpenSearch security");
        commands.put("setup-passwords", new PasswordSetup());
//        commands.put("setup-certificates", new ToBeImplemented());
//        commands.put("rotate-passwords", new ToBeImplemented());
//        commands.put("hasher", new ToBeImplemented());
//        commands.put("audit-config", new ToBeImplemented());
//        commands.put("dynamic-config", new ToBeImplemented());
    }

    private static void printHelp() {
        System.out.println("\nSupported actions:\n");
        for(Map.Entry<String, Command> entry: commands.entrySet()) {
            System.out.println(String.format("%-20s%s", entry.getKey(), entry.getValue().describe()));
        }
        System.out.println("\nCheck usage with: ");
        System.out.println("opensearch-security [action] --help");
        System.out.println("If you face any issues, please report at https://github.com/opensearch-project/security/issues/new/choose");
    }

    public static void main(String[] args) throws Exception {
        if(args.length == 0 || !commands.containsKey(args[0])) {
            printHelp();
            return;
        }
        else if(helpOptions.contains(args[1])) {
            commands.get(args[0]).usage();
            return;
        }

        try {
            commands.get(args[0]).main(Arrays.copyOfRange(args, 0, args.length));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
