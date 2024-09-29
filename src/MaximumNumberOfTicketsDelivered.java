import java.io.*;
import java.util.List;
import java.util.*;

public class MaximumNumberOfTicketsDelivered {

        // To read tickets/releases from the input file
        private static List<int[]> readInputTicketFile(String filename) {
            List<int[]> tickets = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(" ");
                    int dateOfDelivery = Integer.parseInt(parts[0]);
                    int completionTimelines = Integer.parseInt(parts[1]);
                    tickets.add(new int[]{dateOfDelivery, completionTimelines});
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return tickets;
        }

        public static void main(String[] args) {
            //passing the input file name in the input file method
            List<int[]> tickets = readInputTicketFile("./src/releases.txt");

            // Sort tickets based on date of delivery which is first value of the input pair
            Collections.sort(tickets, Comparator.comparingInt(a -> a[0]));

            // To pick the  maximum number of tickets  that can fit within the sprint of 10 days
            List<int[]> selectedTickets = new ArrayList<>();
            int finalDay = 0;

            for (int[] ticket : tickets) {
                int startDay = ticket[0];
                int timeline = ticket[1];
                int endDay = startDay + timeline - 1;

                //validate if the endDay fits within 10 days of sprint and ticket starts after the last ticket that Bob verified
                if (endDay <= 10 && startDay > finalDay) {
                    selectedTickets.add(new int[]{startDay, endDay});
                    finalDay = endDay;
                }
            }

            // Write the solution/output to the "solution.txt" file
            outputSolutionWriteFile(selectedTickets, "./src/solution.txt");
        }

        // To write the solution/output
        private static void outputSolutionWriteFile(List<int[]> tickets, String filename) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
                //this writes the total number of tickets/releases
                bw.write(tickets.size() + "\n");
                // this writes the selected release tickets with start day and end day
                for (int[] ticket : tickets) {
                    bw.write(ticket[0] + " " + ticket[1] + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

