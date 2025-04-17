<h1>📊 ePortfolio Investment Management System</h1>

<p>
A Java-based GUI application that simulates an investment portfolio system, supporting the buying and selling of <strong>stocks</strong> and <strong>mutual funds</strong>.
Built as a <em>school project</em>, this system helps users manage investments, update prices, calculate gains, and perform advanced searches using keywords, symbols, and price ranges—optimized using <code>HashMaps</code>.
</p>

<hr />

<h2>📌 Problem Statement</h2>
<p>
Create a GUI-based application to emulate an investment portfolio manager with two types of investments: <strong>stocks</strong> and <strong>mutual funds</strong>. Users must be able to:
</p>
<ul>
  <li>Buy and sell investments with input validation</li>
  <li>Update investment prices manually</li>
  <li>View gains from investments</li>
  <li>Search investments using symbol, keywords, and price range</li>
  <li>Perform fast keyword searches using <code>HashMaps</code></li>
</ul>

<hr />

<h2>✅ Solution</h2>
<p>
The solution is a functional GUI application with intuitive panels for each major action:
</p>
<ul>
  <li><strong>Buy Panel</strong> – Add or update investments</li>
  <li><strong>Sell Panel</strong> – Sell investments and view gain</li>
  <li><strong>Update Panel</strong> – Manually update prices</li>
  <li><strong>Search Panel</strong> – Use filters like symbol, keywords, price range</li>
  <li><strong>Get Gain Panel</strong> – Show total capital gain</li>
  <li><strong>Quit Button</strong> – Exit the application safely</li>
</ul>

<p>
A <code>HashMap&lt;String, ArrayList&lt;Integer&gt;&gt;</code> stores keywords for fast lookup, enhancing performance when filtering investments.
</p>

<hr />

<h2>🛠 Tech Stack</h2>
<table>
  <tr><td><strong>Language</strong></td><td>Java</td></tr>
  <tr><td><strong>GUI Framework</strong></td><td>Java Swing</td></tr>
  <tr><td><strong>Data Structures</strong></td><td>ArrayList, HashMap</td></tr>
  <tr><td><strong>IDE</strong></td><td>IntelliJ / Eclipse / VS Code</td></tr>
  <tr><td><strong>JDK Version</strong></td><td>Java 8 or higher</td></tr>
</table>

<hr />

<h2>📥 How to Download and Run</h2>

<h4>🔧 Requirements</h4>
<ul>
  <li>Java Development Kit (JDK 8+)</li>
</ul>

<h4>📦 Steps</h4>
<pre><code>
# Clone the repository
git clone https://github.com/gcheema05/ePortfolio-Investment-Management-System.git

# Navigate to the project directory
cd ePortfolio-Investment-Management-System

# Compile the project
javac ePortfolio/*.java

# Run the application
java ePortfolio.Portfolio
</code></pre>

<hr />

<h2>🧪 Test Plan (Key Features)</h2>

<h4>✔️ Buy</h4>
<ul>
  <li>Handle new and existing investments</li>
  <li>Validate symbol, quantity, and price</li>
  <li>Update keyword index in HashMap</li>
</ul>

<h4>✔️ Sell</h4>
<ul>
  <li>Validate inputs and quantities</li>
  <li>Handle full/partial sales</li>
  <li>Display accurate gain and update HashMap</li>
</ul>

<h4>✔️ Update</h4>
<ul>
  <li>Prompt user to enter updated prices for all investments</li>
</ul>

<h4>✔️ Search</h4>
<ul>
  <li>Filter using symbol, keywords, and price range</li>
  <li>Support complex combinations of search inputs</li>
  <li>Use HashMap for optimized keyword searches</li>
</ul>

<h4>✔️ Get Gain</h4>
<ul>
  <li>Calculate and display total gain</li>
  <li>Test for empty portfolio case</li>
</ul>

<h4>✔️ Quit</h4>
<ul>
  <li>Close the application cleanly and notify the user</li>
</ul>

<hr />

<h2>🚀 Future Enhancements</h2>
<ul>
  <li>🔐 User authentication for secure access</li>
  <li>💰 Add more investment types like bonds or crypto</li>
  <li>📈 Integrate real-time market APIs</li>
  <li>☁️ Persist data using file storage or databases</li>
  <li>🎨 Improve UI/UX with better design and charting</li>
</ul>
