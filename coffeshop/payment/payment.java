package payment;

import java.util.ArrayList;
import java.util.Scanner;

public class payment {
    static ArrayList<Order> orders = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int pilih;

        do {
            System.out.println("-----------------------");
            System.out.println("    BUCK COFFEE         ");
            System.out.println("-----------------------");
            System.out.println("1.Buy Coffee");
            System.out.println("2.Check Out");
            System.out.println("3.Exit");
            System.out.println("-----------------------");
            System.out.print("Pilihanmu :");
            pilih = sc.nextInt();
            sc.nextLine();

            if (pilih == 1) {
                orders = buyCoffee(orders);
            } else if (pilih == 2) {
                orders = checkOut(orders);
            }

        } while (pilih != 3);
        orders.clear();
    }

    private static ArrayList<Order> buyCoffee(ArrayList<Order> orders) {
        String nama, tipe, gula;
        int qty;

        System.out.print("Input nama kopi : ");
        nama = sc.nextLine();

        boolean ok;
        do {
            System.out.print("Input nama tipe [Cappucino, Latte, Americano]: ");
            tipe = sc.nextLine();
            ok = cekKopi(tipe);
            if (!ok) {
                System.out.println("Tipe kopi tidak valid. Silakan coba lagi.");
            }
        } while (!ok);

        do {
            System.out.print("Tambah gula [Y/T]: ");
            gula = sc.nextLine();
            ok = cekGula(gula);
            if (!ok) {
                System.out.println("Pilihan gula tidak valid. Silakan coba lagi.");
            }
        } while (!ok);

        System.out.print("Quantity: ");
        qty = sc.nextInt();

        // ArrayList
        orders.add(new Order(nama, tipe, gula, qty));

        return orders;
    }

    private static ArrayList<Order> checkOut(ArrayList<Order> orders) {
        int harga, jumblah, total = 0, bayar;

        System.out.println("Jumlah order: " + String.valueOf(orders.size()));
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.printf("| %-5s| %-15s| %-13s| %-13s| %-13s| %-13s| %-13s|%n",
                "No.", "Name", "Type", "Extra", "Qty", "Price", "Total");
        System.out.println("----------------------------------------------------------------------------------------------------");

        int num = 1;
        for (int i = 0; i < orders.size(); i++) {
            harga = (orders.get(i).getQty() * orders.get(i).getName().length() * 100);

            if (orders.get(i).getSugar().equalsIgnoreCase("Y")) {
                jumblah = orders.get(i).getQty() * harga;
            } else {
                jumblah = (int) ((orders.get(i).getQty() * harga) + (orders.get(i).getQty() * 0.03));
            }
            System.out.printf("| %-5s| %-15s| %-13s| %-13s| %-13s| %-13s| %-13s|%n",
                    num++,
                    orders.get(i).getName(),
                    orders.get(i).getType(),
                    orders.get(i).getSugar(),
                    orders.get(i).getQty(),
                    harga,
                    jumblah);

            total += jumblah;
        }

        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("Total: " + total);
        boolean ok;
        do {
            System.out.print("Bayar: ");
            bayar = sc.nextInt();
            ok = cekBayar(total, bayar);
            if (!ok) {
                System.out.println("Jumlah pembayaran kurang. Silakan coba lagi.");
            }
        } while (!ok);

        orders.clear();

        System.out.println("Kembalian: " + Math.abs(total - bayar));
        System.out.println("Successfully add new order!");
        System.out.println("Press enter to continue....");

        sc.nextLine();
        sc.nextLine();

        return orders;
    }

    private static boolean cekBayar(int total, int bayar) {
        return bayar >= total;
    }

    private static boolean cekKopi(String tipe) {
        return tipe.equalsIgnoreCase("Cappucino") ||
                tipe.equalsIgnoreCase("Latte") ||
                tipe.equalsIgnoreCase("Americano");
    }

    private static boolean cekGula(String gula) {
        return gula.equalsIgnoreCase("Y") || gula.equalsIgnoreCase("T");
    }
}

