import java.io.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.OutputStream;
import java.io.PrintStream;

public class AppMain extends JFrame {

    private static final String FILE_PATH = "D:\\BS CS AI\\3rd Semester\\DSA\\DSA Theory Project\\employeesGUI.txt";;  

    private final Employee_Management_System emp;
    private final JTextArea outputArea;
    private final DefaultTableModel tableModel;
    private final JTable table;

    private final Color BG = new Color(240, 248, 255);
    private final Color PANEL = new Color(220, 230, 250);
    private final Color ACCENT = new Color(60, 130, 200);
    private final Color ACCENT_HOVER = new Color(90, 160, 230);
    private final Color TEXT = Color.BLACK;

    public AppMain() {

        emp = new Employee_Management_System();

        
        EmployeeFileHandler.loadEmployees(emp, FILE_PATH);

        setTitle(" EmployeesPRO - StaffFusion");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(BG);
        setResizable(false);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(180, 200, 230));
        header.setBorder(new EmptyBorder(12, 16, 12, 16));
        JLabel lblTitle = new JLabel("EmployeesPRO");
        lblTitle.setForeground(TEXT);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        header.add(lblTitle, BorderLayout.WEST);

        JLabel lblSub = new JLabel("Staff Fusion ");
        lblSub.setForeground(new Color(60, 60, 60));
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        header.add(lblSub, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);

        JPanel left = new JPanel();
        left.setBackground(PANEL);
        left.setPreferredSize(new Dimension(280, 0));
        left.setLayout(null);

        JLabel menu = new JLabel("General Interface");
        menu.setFont(new Font("Segoe UI", Font.BOLD, 18));
        menu.setForeground(TEXT);
        menu.setBounds(20, 20, 240, 30);
        left.add(menu);

        JButton btnManager = themedButton("Manager View");
        btnManager.setBounds(30, 80, 220, 42);
        left.add(btnManager);

        JButton btnEmployee = themedButton("Employee View");
        btnEmployee.setBounds(30, 140, 220, 42);
        left.add(btnEmployee);

        JButton btnPreload = themedButton("Preload Employees");
        btnPreload.setBounds(30, 200, 220, 42);
        left.add(btnPreload);

        JButton btnRefresh = themedButton("Refresh Table");
        btnRefresh.setBounds(30, 260, 220, 42);
        left.add(btnRefresh);

        JButton btnClearOutput = themedButton("Clear Output");
        btnClearOutput.setBounds(30, 320, 220, 42);
        left.add(btnClearOutput);

        JButton btnExit = themedButton("Exit System");
        btnExit.setBounds(30, 380, 220, 42);
        btnExit.setBackground(new Color(180, 50, 60));
        left.add(btnExit);

        add(left, BorderLayout.WEST);

        JPanel center = new JPanel(new BorderLayout());
        center.setBackground(BG);

        String[] cols = {"ID", "Name", "Department", "Education", "Experience", "Grade", "Salary"};
        tableModel = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        table = new JTable(tableModel);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setRowHeight(26);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setBorder(new EmptyBorder(12,12,12,12));
        tableScroll.setPreferredSize(new Dimension(680, 280));
        center.add(tableScroll, BorderLayout.NORTH);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        outputArea.setBackground(new Color(245, 250, 255));
        outputArea.setForeground(Color.BLACK);
        JScrollPane outputScroll = new JScrollPane(outputArea);
        outputScroll.setBorder(new EmptyBorder(12,12,12,12));
        center.add(outputScroll, BorderLayout.CENTER);

        add(center, BorderLayout.CENTER);

        JPanel right = new JPanel(null);
        right.setPreferredSize(new Dimension(240, 0));
        right.setBackground(PANEL);

        JLabel actionLabel = new JLabel("Manager Tasks");
        actionLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        actionLabel.setForeground(TEXT);
        actionLabel.setBounds(30, 20, 180, 30);
        right.add(actionLabel);

        JButton btnViewAll = themedButton("View All");
        btnViewAll.setBounds(30, 80, 180, 36);
        right.add(btnViewAll);

        JButton btnTotal = themedButton("Total Employees");
        btnTotal.setBounds(30, 130, 180, 36);
        right.add(btnTotal);

        JButton btnAdd = themedButton("Add Employee (Form)");
        btnAdd.setBounds(30, 180, 180, 36);
        right.add(btnAdd);

        JButton btnRemove = themedButton("Remove Employee");
        btnRemove.setBounds(30, 230, 180, 36);
        right.add(btnRemove);

        JButton btnSearch = themedButton("Search Employee");
        btnSearch.setBounds(30, 280, 180, 36);
        right.add(btnSearch);

        JButton btnLogout = themedButton("Logout Manager");
        btnLogout.setBounds(30, 340, 180, 36);
        right.add(btnLogout);

        add(right, BorderLayout.EAST);

        btnManager.addActionListener(e -> {
            JPanel login = new JPanel(new GridLayout(4,1,4,4));
            login.setBackground(new Color(225, 237, 255));
            JTextField user = new JTextField();
            JPasswordField pass = new JPasswordField();

            login.add(new JLabel("Manager:"));
            login.add(user);
            login.add(new JLabel("Password:"));
            login.add(pass);

            int res = JOptionPane.showConfirmDialog(
                    this, login, "Manager Login",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
            );

            if (res == JOptionPane.OK_OPTION) {
                String p = new String(pass.getPassword());
                if (p.equalsIgnoreCase("admin12")) {
                    appendOutput("Manager authenticated.\n");
                    enableManagerActions(true, btnViewAll, btnTotal, btnAdd, btnRemove, btnSearch, btnLogout);
                } else {
                    appendOutput("Invalid manager password.\n");
                    JOptionPane.showMessageDialog(this, "Invalid password", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnEmployee.addActionListener(e -> openEmployeeDialog());

        btnPreload.addActionListener(e -> {
            emp.alreadyAddedEmployees();
            appendOutput("Preloaded employees.\n");
            refreshTable();

            // ------------ FIXED SAVE ----------
            EmployeeFileHandler.saveEmployees(emp.head, FILE_PATH);
        });

        btnRefresh.addActionListener(e -> {
            refreshTable();
            appendOutput("Table refreshed.\n");
        });

        btnClearOutput.addActionListener(e -> outputArea.setText(""));

        btnExit.addActionListener(e -> {
            int c = JOptionPane.showConfirmDialog(this, "Exit the system?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (c == JOptionPane.YES_OPTION) System.exit(0);
        });

        btnViewAll.addActionListener(e -> {
            emp.viewEmployees();
            appendOutput("[viewEmployees executed]\n");
            refreshTable();
        });

        btnTotal.addActionListener(e -> emp.TotalEmployees());

        btnAdd.addActionListener(e -> addEmployeeForm());
        btnRemove.addActionListener(e -> removeDialog());
        btnSearch.addActionListener(e -> searchDialog());
        btnLogout.addActionListener(e -> {
            enableManagerActions(false, btnViewAll, btnTotal, btnAdd, btnRemove, btnSearch, btnLogout);
            appendOutput("Manager logged out.\n");
        });

        enableManagerActions(false, btnViewAll, btnTotal, btnAdd, btnRemove, btnSearch, btnLogout);

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int r = table.getSelectedRow();
                    if (r >= 0) {
                        int id = Integer.parseInt(tableModel.getValueAt(r, 0).toString());
                        emp.searchById(id);
                        appendOutput("[searchById(" + id + ") executed]\n");
                    }
                }
            }
        });

        redirectSystemStreams();
        refreshTable();

        // ------- FIXED SHUTDOWN SAVE -------
        Runtime.getRuntime().addShutdownHook(new Thread(() ->
                EmployeeFileHandler.saveEmployees(emp.head, FILE_PATH)
        ));
    }


    private void addEmployeeForm() {

        JPanel p = new JPanel(new GridLayout(7,2,6,6));
        p.setBackground(PANEL);
        JTextField nameF = new JTextField();
        JTextField deptF = new JTextField();
        JTextField idF = new JTextField();
        JTextField eduF = new JTextField();
        JTextField expF = new JTextField();
        JTextField gradeF = new JTextField();
        JTextField salF = new JTextField();

        p.add(new JLabel("Name:")); p.add(nameF);
        p.add(new JLabel("Department:")); p.add(deptF);
        p.add(new JLabel("Employee ID (int):")); p.add(idF);
        p.add(new JLabel("Education:")); p.add(eduF);
        p.add(new JLabel("Experience (float):")); p.add(expF);
        p.add(new JLabel("Grade (char):")); p.add(gradeF);
        p.add(new JLabel("Salary (int):")); p.add(salF);

        int res = JOptionPane.showConfirmDialog(this, p, "Add New Employee",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (res == JOptionPane.OK_OPTION) {
            try {
                String name = nameF.getText().trim();
                String dept = deptF.getText().trim();
                int id = Integer.parseInt(idF.getText().trim());
                String edu = eduF.getText().trim();
                float exp = Float.parseFloat(expF.getText().trim());
                char grade = gradeF.getText().trim().charAt(0);
                int sal = Integer.parseInt(salF.getText().trim());

                emp.addEmployees(name, dept, id, edu, exp, grade, sal);

                // -------- FIXED SAVE ----------
                EmployeeFileHandler.saveEmployees(emp.head, FILE_PATH);

                appendOutput("Employee added.\n");
                refreshTable();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        this, "Invalid input.", "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }


    private void removeDialog() {
        String[] opts = {"By Name", "By ID"};
        int ch = JOptionPane.showOptionDialog(this, "Remove employee by:", "Remove",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, opts, opts[0]);

        if (ch == 0) {
            String name = JOptionPane.showInputDialog(this, "Enter name to remove:");
            if (name != null && !name.trim().isEmpty()) {
                emp.removeByName(name.trim());

                // -------- FIXED SAVE ----------
                EmployeeFileHandler.saveEmployees(emp.head, FILE_PATH);

                appendOutput("Removed by name.\n");
                refreshTable();
            }
        }
        else if (ch == 1) {
            String idStr = JOptionPane.showInputDialog(this, "Enter ID to remove:");
            if (idStr != null) {
                try {
                    int id = Integer.parseInt(idStr.trim());
                    emp.removeByID(id);

                    // -------- FIXED SAVE ----------
                    EmployeeFileHandler.saveEmployees(emp.head, FILE_PATH);

                    appendOutput("Removed by ID.\n");
                    refreshTable();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Invalid ID.");
                }
            }
        }
    }


    private void searchDialog() {
        String[] opts = {"By Name", "By ID", "By Department", "By Experience"};
        String sel = (String) JOptionPane.showInputDialog(this,
                "Select search type:", "Search",
                JOptionPane.PLAIN_MESSAGE, null, opts, opts[0]);

        if (sel == null) return;

        switch (sel) {
            case "By Name" -> {
                String s = JOptionPane.showInputDialog(this, "Enter name:");
                if (s != null) emp.searchByName(s.trim());
            }
            case "By ID" -> {
                String s = JOptionPane.showInputDialog(this, "Enter ID:");
                if (s != null) {
                    try { emp.searchById(Integer.parseInt(s.trim())); }
                    catch (Exception ex) { JOptionPane.showMessageDialog(this, "Invalid ID."); }
                }
            }
            case "By Department" -> {
                String s = JOptionPane.showInputDialog(this, "Enter department:");
                if (s != null) emp.searchByDepartment(s.trim());
            }
            case "By Experience" -> {
                String s = JOptionPane.showInputDialog(this, "Enter experience (float):");
                if (s != null) {
                    try { emp.searchByExperience(Float.parseFloat(s.trim())); }
                    catch (Exception ex) { JOptionPane.showMessageDialog(this, "Invalid value."); }
                }
            }
        }
    }


    private void openEmployeeDialog() {
        JPanel p = new JPanel(new GridLayout(4,1,6,6));
        p.setBackground(PANEL);
        JTextField idF = new JTextField();
        JTextField codeF = new JTextField();
        p.add(new JLabel("Employee ID:"));
        p.add(idF);
        p.add(new JLabel("CODE (old/new):"));
        p.add(codeF);

        int r = JOptionPane.showConfirmDialog(this, p, "Employee Access",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (r == JOptionPane.OK_OPTION) {
            try {
                int id = Integer.parseInt(idF.getText().trim());
                String code = codeF.getText().trim();

                if ((id >= 101 && id <= 115 && code.equalsIgnoreCase("old")) ||
                    (id >= 116  && code.equalsIgnoreCase("new"))) {

                    emp.searchById(id);
                    appendOutput("Employee view executed.\n");

                } else {
                    JOptionPane.showMessageDialog(
                            this, "Invalid ID or CODE.", "Access Denied",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID.");
            }
        }
    }


    private void enableManagerActions(boolean on, JButton... btns) {
        for (JButton b : btns) b.setEnabled(on);
    }

    private void refreshTable() {
        SwingUtilities.invokeLater(() -> {
            tableModel.setRowCount(0);
            Node cur = emp.head;
            while (cur != null) {
                Object[] row = {cur.employee_id, cur.name, cur.department,
                        cur.education, cur.experience, cur.grade, cur.salary};
                tableModel.addRow(row);
                cur = cur.next;
            }
        });
    }

    private void appendOutput(String s) {
        SwingUtilities.invokeLater(() -> {
            outputArea.append(s);
            outputArea.setCaretPosition(outputArea.getDocument().getLength());
        });
    }

    private JButton themedButton(String text) {
        JButton b = new JButton(text);
        b.setFont(new Font("Segoe UI", Font.BOLD, 13));
        b.setBackground(ACCENT);
        b.setForeground(TEXT);
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createEmptyBorder(6, 10, 6, 10));
        b.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) { b.setBackground(ACCENT_HOVER); }
            public void mouseExited(java.awt.event.MouseEvent evt) { b.setBackground(ACCENT); }
        });
        return b;
    }

    private void redirectSystemStreams() {
        OutputStream out = new OutputStream() {
            private final StringBuilder sb = new StringBuilder();
            @Override
            public void write(int b) {
                sb.append((char)b);
                if (b == '\n') {
                    final String text = sb.toString();
                    SwingUtilities.invokeLater(() -> {
                        outputArea.append(text);
                        outputArea.setCaretPosition(outputArea.getDocument().getLength());
                    });
                    sb.setLength(0);
                }
            }
            @Override
            public void write(byte[] b, int off, int len) {
                String s = new String(b, off, len);
                SwingUtilities.invokeLater(() -> {
                    outputArea.append(s);
                    outputArea.setCaretPosition(outputArea.getDocument().getLength());
                });
            }
        };

        PrintStream ps = new PrintStream(out, true);
        System.setOut(ps);
        System.setErr(ps);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                AppMain gui = new AppMain();
                gui.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
