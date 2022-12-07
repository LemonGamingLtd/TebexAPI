# TebexAPI

TebexAPI brings [Tebex](https://tebex.io) and Java together. This is made for developers that want to use Tebex on their Minecraft servers that just want to show their lastest donators on signs or create cool statues without using the whole Buycraft plugin.

## Installation
TebexAPI is distributed as a [maven](http://maven.apache.org/) project. To compile it and install it in your local Maven repository (.m2) use:

```
git clone https://github.com/LemonGamingLtd/TebexAPI
cd TebexAPI
mvn clean install
```

## Usage
To start getting some data you need to create an instance of `TebexAPI` where you have to provide your private API Server Key.

Then, you can change if you want the queries to be made via HTTPS or HTTP with `TebexAPI.setSecure(boolean)`. By default the API will use HTTPS.

Now you can use all the methods inside this class. If you don't know how something works, read the Javadocs, all the methods are well documented.

The methods in this API are blocking, so that means you will have to run the methods in another `Thread`, or if you are using [Spigot](https://spigotmc.org) just use `BukkitRunnable.runTaskAsynchronously(Plugin)`

## Examples
Get 10 latest people that bought a package and how much they payed:

```java
try {
    TebexApi api = new TebexApi("YOUR_API_KEY");
    Set<Payment> latest = api.getLatestPayments(10);
    
    for (Payment payment : latest){
        System.out.println("- " + payment.getPlayerName() + " payed " + payment.getAmount() + payment.getCurrencySymbol());
    }
    
} catch (TebexException e) {
    e.printStackTrace();
}
```

Get all the Packages and display them:
```java
try {
    TebexApi api = new TebexApi("YOUR_API_KEY");
    Set<Category> categories = api.getListing();
    
    for (Category category : categories){
        System.out.println("- " + category.getName() + " has " + category.getPackages().size() + " packages and " + category.getSubCategories().size() + " subcategories");
    }
} catch (TebexException e){
    e.printStackTrace();
}
```

## License
This project is licensed under the GNU General Public License. You can read all the license terms [here](LICENSE)

## Donate
If you think this project is useful for you or your server, please, consider donating [here](https://hugmanrique.me/donate/)
