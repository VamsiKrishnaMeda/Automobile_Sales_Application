# Automobile Sales Application

This application is part CSC550 Database Management System course at Northern Kentucky University.

**Application Description**

The application consists of (some of) the operations of an automobile company,
such as General Motors, Ford, Toyota, or Volkswagen. The company needs to keep track of brands, models,
vehicles and their options, dealers and customers. Focus on the following aspects of corporate operations:

  * __Brands:__ The company may have several brands (for example, GM has Chevrolet, Pontiac, Buick,
Cadillac, GMC, Saturn, Hummer, Saab, Daewoo, Holden, Vauxhall, and Opel and Volkswagen has
Volkswagen, Audi, Lamborghini, Bentley, Bugatti, Skoda, and SEAT). Each brand has at least a name
and a logo.
  * __Models:__ Each brand offers several models (for example, Buick’s models are the Enclave, LaCrosse,
and Lucerne, and Mercury’s models are the Mariner, Milan, Sable, and Grand Marquis). Each model
may come in a variety of body styles (4-door, wagon, etc.)
  * __Vehicles:__ Each vehicle has a vehicle identification number (VIN). Lots of information is encoded in
real VINs (they are well described on Wikipedia), but you can just make them up. Each vehicle has
options: we’ll stick to color, engine and transmission. Also record the production date.
  * __Dealers and Customers:__ Dealers buy vehicles from the manufacturer and sell them to customers.
We’ll keep track of sales by date, brand, model, and color; and also by dealer. Dealers keep some cars
in inventory. Some, of course, are already sold, but the dealer still keeps track of that fact. You must
record the tag price and the price for which a vehicle is sold.
  * __Customers:__ In reality, lots of demographic data are gathered. We’ll stick to name, address, phone,
gender, and annual income for individual buyers. In reality, the customer may also be a company (e.g.
Hertz, Avis), but this office focuses on individual clients rather than companies.

**Queries**

You should run a number of test queries to see that you have loaded your database in the way you
intended. The queries listed below are those that your client (the automobile company manager) wants turned
in. They may provide further hints about database design, so think about them at the outset of the project.
  * Suppose that it is found that manual transmission systems produced between two given dates are
defective. Find the VIN of each car containing such a transmission and the customer to which it was
sold; the vehicle production date should be in the interval when the defective transmissions were
produced.
  * Show sales trends for various brands over the past 3 years, by year, buyer gender, and buyer income
range.
  * Find the top brand(s) by dollar-amount sold in the past year.
  * Find the top dealer(s) by dollar-amount sold in the past year.
  * Find the top brand(s) by unit sales in the past year.
  * In what month(s) do convertibles sell best?
  * Find the dealer(s) who keeps a vehicle in inventory for the longest average time.

**Interfaces**

There are several types of users who access the database. Each may need a special application:
  * The database administrator (you) may use SQL either via the command line or SQL Developer.
  * The company’s production office needs an interface to allow new vehicles to be recorded in the
database and to assign them to various dealers’ inventories.
  * A dealer’s office needs an interface to record sales of vehicles in their inventory.
  * The company’s marketing office must be able to run the analysis queries listed in the Queries section
above (except first query).
  * Online customers need an interface to find dealers and check products, inventories, and prices.
