package clinica.app;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

                                                                                                       
public class Console {
    private static final Scanner entrada = new Scanner(System.in);
    public static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static String texto(String mensagem) {
        System.out.print(mensagem + ": ");
        return entrada.nextLine().trim();
    }

    public static int inteiro(String mensagem) {
        while (true) {
            try {
                return Integer.parseInt(texto(mensagem));
            } catch (NumberFormatException e) {
                System.out.println("  Valor inválido. Digite um número inteiro.");
            }
        }
    }

    public static double decimal(String mensagem) {
        while (true) {
            try {
                return Double.parseDouble(texto(mensagem).replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("  Valor inválido. Digite um número (ex.: 150,00).");
            }
        }
    }

    public static LocalDate data(String mensagem) {
        while (true) {
            try {
                return LocalDate.parse(texto(mensagem + " (dd/mm/aaaa)"), FORMATO_DATA);
            } catch (DateTimeParseException e) {
                System.out.println("  Data inválida. Use o formato dd/mm/aaaa.");
            }
        }
    }

    public static LocalTime hora(String mensagem) {
        while (true) {
            try {
                return LocalTime.parse(texto(mensagem + " (hh:mm)"));
            } catch (DateTimeParseException e) {
                System.out.println("  Hora inválida. Use o formato hh:mm.");
            }
        }
    }

                                                                       
    public static <E extends Enum<E>> E escolher(String mensagem, E[] opcoes) {
        System.out.println(mensagem);
        for (int i = 0; i < opcoes.length; i++) System.out.println("  " + (i + 1) + " - " + opcoes[i]);
        while (true) {
            int n = inteiro("Opção");
            if (n >= 1 && n <= opcoes.length) return opcoes[n - 1];
            System.out.println("  Opção inexistente.");
        }
    }

    public static boolean confirmar(String mensagem) {
        return texto(mensagem + " (s/n)").equalsIgnoreCase("s");
    }

    public static void titulo(String t) {
        System.out.println("\n===== " + t.toUpperCase() + " =====");
    }

    public static void pausa() { texto("\n[Enter para continuar]"); }
}
