# DeliveryForYou

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

### Vice Administration

1. **Retrieve Short Deliveries by Date:**
   - Retrieves all short deliveries on a specified date.

2. **Presentation of Members with More Than 3 Short Deliveries:**
   - Presents information on all members with more than 3 short deliveries.

3. **Short Shipment Deletion Form:**
   - Provides a form for deleting short shipments.

4. **Form for Adding Short Shipment:**
   - Allows the addition of a short shipment.

### Main Screen

- **Finishing Button:**
   - Finishes and exits the system.

## Database Class

The `Database` class is a service class containing static values and includes:

1. `countTree` - Integer value.
2. `systemAdministrators` - ArrayList of system administrators.
3. `ourLastDeliveries` - TreeMap of recent shipments ordered by subscribers.
   - Key: Name of the subscriber.
   - Value: The corresponding delivery.
4. `ourMembersAndDeliveries1` - TreeMap containing the current value of `countTree` with customer names.
5. `ourMembersAndDeliveries2` - TreeMap containing the current value of `countTree` with shipments of the same customer.



