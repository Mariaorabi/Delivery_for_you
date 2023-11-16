# DeliveryForYou
![image](https://github.com/Mariaorabi/Delivery_for_you/assets/143322072/e95268ed-79a4-4380-b95f-c8c66c718b69)


DeliveryForYou is a prototype for the "Shipping for You" company implemented in Java. The system manages deliveries, including regular deliveries, express deliveries, and business deliveries. The system comprises members, each with their details, residential area (north, center, or south), and an array of deliveries of various types.

The main system consists of an array of managers, each responsible for subscribers in a specific region (north, center, or south). The system administrator has a username and password stored in variables within the administrator class.

### Main Managers Menu

1. **Add Subscription:**
   - Allows the addition of subscription details to the manager's system.
   - Provides functionality to delete a subscription using a subscription code.
   - Includes a button to save subscribers who ordered short deliveries along with delivery details in a text file.

2. **Add VICE Manager:**
   - Enables the addition of a VICE manager to the system.

3. **Retrieve Deliveries by Customer Code:**
   - Retrieves all regular and short deliveries of a customer using their code.

4. **Add New Shipment:**
   - Adds a new shipment to an existing customer's array in the manager's system.
   - Displays all regular shipments in the manager's responsible area in a JTable.
   - Includes a short shipment deletion form.

5. **Add New Manager:**
   - Adds a new manager to the system.

6. **Submenu:**
   - Contains a MenuItem for:
      - Displaying all short deliveries within a JTable.
      - Displaying details of all subscribers who ordered short delivery within a TextArea.
      - Listing all cities to which a short shipment was sent in the last 30 days.

![image](https://github.com/Mariaorabi/Delivery_for_you/assets/143322072/636a25c2-d3ce-4d24-af1e-17bd5d9bbd66)

### Vice Administration

1. **Retrieve Short Deliveries by Date:**
   - Retrieves all short deliveries on a specified date.

2. **Presentation of Members with More Than 3 Short Deliveries:**
   - Presents information on all members with more than 3 short deliveries.

3. **Short Shipment Deletion Form:**
   - Provides a form for deleting short shipments.

4. **Form for Adding Short Shipment:**
   - Allows the addition of a short shipment.

![image](https://github.com/Mariaorabi/Delivery_for_you/assets/143322072/d54f1253-129c-4bea-8fdf-2ef777fc60ad)

### Main Screen

- **Finishing Button:**
   - Finishes and exits the system.

## Database 

The `Database` class is a service class containing static values and includes:

1. `countTree` - Integer value.
2. `systemAdministrators` - ArrayList of system administrators.
3. `ourLastDeliveries` - TreeMap of recent shipments ordered by subscribers.
   - Key: Name of the subscriber.
   - Value: The corresponding delivery.
4. `ourMembersAndDeliveries1` - TreeMap containing the current value of `countTree` with customer names.
5. `ourMembersAndDeliveries2` - TreeMap containing the current value of `countTree` with shipments of the same customer.

# DeliveryForYou


## How to Run

To run the code, follow these steps:

1. **Download the Code:**
   - Clone or download the project ZIP file from the repository.

2. **Extract the ZIP:**
   - Extract the contents of the ZIP file to your preferred location.

3. **Open in Eclipse:**
   - Open Eclipse IDE.

4. **Import Project:**
   - Go to `File` > `Import`.
   - Select `General` > `Existing Projects into Workspace`.
   - Choose the extracted project folder as the root directory.
   - Click `Finish`.

5. **Build and Run:**
   - Once the project is imported, right-click on the project in the `Project Explorer`.
   - Select `Build Project` to compile the code.
   - After building, right-click on the main class file.
   - Choose `Run As` > `Java Application`.

6. **GUI Display:**
   - The GUI for "DeliveryForYou" should now be displayed.

**Note:** Ensure you have Eclipse IDE and Java Development Kit (JDK) installed on your system.



