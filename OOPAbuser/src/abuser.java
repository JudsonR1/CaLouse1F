import java.util.*;

public class abuser {

    public static void main(String[] args) {
        int choice = 0;
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> roles = new ArrayList<>();
        ArrayList<Integer> HPs = new ArrayList<>();
        ArrayList<Integer> magicDamages = new ArrayList<>();
        ArrayList<Integer> criticalDamages = new ArrayList<>();
        ArrayList<Integer> physicalDamages = new ArrayList<>();
        ArrayList<Boolean> isFreeSkins = new ArrayList<>();
        boolean exit = false;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Welcome to Mobile Legends Administrartor: \n1.Insert Hero\n2.View Hero\n3.Update Hero\n4.Delete Hero\n5.Exit");
            System.out.print("insert your choice : ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("========================= Insert Hero =========================");
                    System.out.printf("Insert Hero Name : ");
                    names.add(sc.nextLine());
                    String role;
                    do {
                        System.out.printf("Insert Hero Role : ");
                        role = sc.nextLine();
                    } while (!role.equals("Fighter") && !role.equals("Assasin") && !role.equals("Tank") && !role.equals("Mage") && !role.equals("Marksman") && !role.equals("Support"));
                    roles.add(role);
                    System.out.printf("Insert HP : ");
                    HPs.add(sc.nextInt());
                    sc.nextLine();
                    System.out.printf("Insert Magic Damage : ");
                    magicDamages.add(sc.nextInt());
                    sc.nextLine();
                    System.out.printf("Insert Physical Damage : ");
                    physicalDamages.add(sc.nextInt());
                    sc.nextLine();
                    System.out.printf("Insert Critical Damage : ");
                    criticalDamages.add(sc.nextInt());
                    sc.nextLine();
                    System.out.printf("Is this Hero have free skin? (yes/no) : ");
                    isFreeSkins.add(sc.nextLine().equalsIgnoreCase("yes"));
                    break;
                case 2:
                    if (names.isEmpty()) {
                        System.out.printf("No heroes available. Please add some heroes first\n");
                    }
                    for (int i = 0; i < names.size(); i++) {
                        System.out.println("========================= Hero List =========================");
                        System.out.println("Name : " + names.get(i));
                        System.out.println("Role : " + roles.get(i));
                        System.out.println("HP : " + HPs.get(i));
                        System.out.println("Magic Damage : " + magicDamages.get(i));
                        System.out.println("Physical Damage : " + physicalDamages.get(i));
                        System.out.println("Critical Damage : " + criticalDamages.get(i));
                        if (isFreeSkins.get(i)) {
                            System.out.println("Free Skin to Claim");
                        }
                    }
                    break;
                case 3:
                    String name, newRole;
                    int hp, magicDamage, criticalDamage, physicalDamage;
                    boolean isFreeSkin;
                    System.out.println("========================= Edit Hero =========================");
                    System.out.print("Enter the name of Hero to Edit : ");
                    name = sc.nextLine();
                    int index = names.indexOf(name);
                    if (index != -1) {
                        System.out.print("Change Hero Name : ");
                        names.set(index, sc.nextLine());
                        do {
                            System.out.print("Change Hero Role : ");
                            newRole = sc.nextLine();
                        } while (!newRole.equals("Fighter") && !newRole.equals("Assasin") && !newRole.equals("Tank") && !newRole.equals("Mage") && !newRole.equals("Marksman") && !newRole.equals("Support"));
                        roles.set(index, newRole);
                        System.out.print("Change HP : ");
                        HPs.set(index, sc.nextInt());
                        sc.nextLine();
                        System.out.print("Change Magic Damage : ");
                        magicDamages.set(index, sc.nextInt());
                        sc.nextLine();
                        System.out.print("Change Physical Damage : ");
                        physicalDamages.set(index, sc.nextInt());
                        sc.nextLine();
                        System.out.print("Change Critical Damage : ");
                        criticalDamages.set(index, sc.nextInt());
                        sc.nextLine();
                        System.out.print("Does this Hero have free skin? (yes/no) : ");
                        isFreeSkin = sc.nextLine().equalsIgnoreCase("yes");
                        isFreeSkins.set(index, isFreeSkin);
                    } else {
                        System.out.printf("%s doesen't exist\n", name);
                    }
                    break;
                case 4:
                    System.out.println("========================= Remove Hero =========================");
                    System.out.print("Enter the name of Hero to Remove : ");
                    String removeName = sc.nextLine();
                    int removeIndex = names.indexOf(removeName);
                    if (removeIndex != -1) {
                        names.remove(removeIndex);
                        roles.remove(removeIndex);
                        HPs.remove(removeIndex);
                        magicDamages.remove(removeIndex);
                        physicalDamages.remove(removeIndex);
                        criticalDamages.remove(removeIndex);
                        isFreeSkins.remove(removeIndex);
                        System.out.printf("%s successfully removed\n", removeName);
                    } else {
                        System.out.printf("%s doesn't exist\n", removeName);
                    }
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        } while (!exit);
        sc.close();
    }
}
