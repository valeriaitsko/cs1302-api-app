# Deadline

Modify this file to satisfy a submission requirement related to the project
deadline. Please keep this file organized using Markdown. If you click on
this file in your GitHub repository website, then you will see that the
Markdown is transformed into nice-looking HTML.

## Part 1.1: App Description

> Please provide a friendly description of your app, including
> the primary functions available to users of the app. Be sure to
> describe exactly what APIs you are using and how they are connected
> in a meaningful way.

> **Also, include the GitHub `https` URL to your repository.**

App Description: Users can type in a city, then choose a type of brewery from the drop down menu as well as a country and after pressing the go button will be shown a list of 3 breweries in that city. Then after clicking the learn more button the app will show a screen with information about the country the city is located in. The city typed in as well as the type of brewery chosen by the user is used as part of the uri for the first api. The HttpResponse for the first api returns information including the country of that city,  which is then used to create a uri for the second api, which will return the country information. The dropdown with the countries is used for cases where multiple countries have the same city name, and can be used to filter responses accordingly.

 GitHub Link: https://github.com/valeriaitsko/cs1302-api-app
## Part 1.2: APIs

> For each RESTful JSON API that your app uses (at least two are required),
> include an example URL for a typical request made by your app. If you
> need to include additional notes (e.g., regarding API keys or rate
> limits), then you can do that below the URL/URI. Placeholders for this
> information are provided below. If your app uses more than two RESTful
> JSON APIs, then include them with similar formatting.

### API 1

```
First API Example URI: https://api.openbrewerydb.org/v1/breweries?by_city=Dublin&by_type=brewpub&per_page=3
```

> Replace this line with notes (if needed) or remove it (if not needed).

### API 2

```
Second API Example: https://restcountries.com/v3.1/name/United%20States

```

> Replace this line with notes (if needed) or remove it (if not needed).

## Part 2: New

> What is something new and/or exciting that you learned from working
> on this project?

Something new I learned from the project was the Map interface, which I needed to use to implement the countries api. I think it's a great tool to use when working with outside data sources.

## Part 3: Retrospect

> If you could start the project over from scratch, what do
> you think might do differently and why?

If I could start this project from scratch I might change the front end of the app to look nicer, I think with more time I could really make the app look good.
