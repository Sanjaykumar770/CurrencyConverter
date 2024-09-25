import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter extends JFrame implements ActionListener {

    private JTextField amountField;
    private JComboBox<String> currencyFrom;
    private JComboBox<String> currencyTo;
    private JLabel resultLabel;
    private JButton convertButton;

    private final Map<String, Double> exchangeRates = new HashMap<>();

    public CurrencyConverter() {
        initializeExchangeRates();

        // Set up the frame
        setTitle("Currency Converter");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Input field for amount
        amountField = new JTextField(10);
        add(new JLabel("Amount:"));
        add(amountField);
        String[] currencies = exchangeRates.keySet().toArray(new String[0]);
        currencyFrom = new JComboBox<>(currencies);
        add(currencyFrom);
        add(new JLabel("Convert to:"));
        currencyTo = new JComboBox<>(currencies);
        add(currencyTo);

        // Convert button
        convertButton = new JButton("Convert");
        convertButton.addActionListener(this);
        add(convertButton);

        resultLabel = new JLabel("Result: ");
        add(resultLabel);
        setVisible(true);
    }

    private void initializeExchangeRates() {
        exchangeRates.put("USD", 1.0);
        exchangeRates.put("EUR", 0.85);
        exchangeRates.put("GBP", 0.75);
        exchangeRates.put("JPY", 110.0);
        exchangeRates.put("AUD", 1.35);
        exchangeRates.put("CAD", 1.25);
        exchangeRates.put("CHF", 0.93);
        exchangeRates.put("CNY", 6.45);
        exchangeRates.put("INR", 74.50);
        exchangeRates.put("MXN", 20.20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double amount = Double.parseDouble(amountField.getText());
            String fromCurrency = (String) currencyFrom.getSelectedItem();
            String toCurrency = (String) currencyTo.getSelectedItem();

            double convertedAmount = convertCurrency(amount, fromCurrency, toCurrency);
            resultLabel.setText("Result: " + String.format("%.2f", convertedAmount) + " " + toCurrency);
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid amount entered!");
        }
    }

    private double convertCurrency(double amount, String from, String to) {
        if (from.equals(to)) {
            return amount;
        }

        double amountInUSD = amount / exchangeRates.get(from);

        return amountInUSD * exchangeRates.get(to);
    }

    public static void main(String[] args) {
        new CurrencyConverter();
    }
}
