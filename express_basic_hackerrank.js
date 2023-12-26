
// Reads into recipes
var recipes = require('../recipes.json');
var router = require('express').Router();

module.exports = router;

// Test against `/recipes/shopping-list?ids=1`
// Callback function on API route hit
// `next` for the next callback function
// EXPRESS.JS supports multi-routing many methods
// Same views directory
// https://stackoverflow.com/questions/38906961/node-express-cannot-get-route
// Two seperate files = index and router = for HTTP routing

// /temp/shopping-list works
// Careful with passage of query parameters
// Careful with routing
router.get('/shopping-list', function(req,res) {
  // console.log("Request is", req)
  var queryParams = req.query.ids
  // blank of not present cases
  if(req.query.ids === undefined) { // no exists
    res.status(400).send()
  }
  else if(req.query.ids === '') { // no value param
    res.status(400).send()
  }
  var paramsArr = queryParams.split(',')
  var ingredients = []
  paramsArr.forEach((paramId) => {
    // console.log("Id = ", paramId)
    recipes.forEach((recipe) => {
      // console.log(recipe)
      if(recipe.id === Number(paramId)) {
        ingredients.push(recipe.ingredients)
      }
    })
  })
  if(ingredients.length === 0) {
    res.status(404)
    res.send("NOT_FOUND")
  } 
  res.status(200)
    res.send(ingredients)
  // console.log("Ingredients = ", ingredients)
  // console.log(queryParams)
  // res.send('<p>BODY TO DO</p>')
  // res.send("<p>HERE</p>")
})

/*
Write an Express App
Auto create shopping lists from the Recipes Array

Routes ( only one ) 

  GET /recipes/shopping-list?ids 
  Ret : list[aggr-ingredients]
  Comma-seperate IDs list
    ids : mandatory query parameter
    send status code 400 : blank/not present


  Aggregate ingredients for IDs matching any foudn in recipe list
  `recipes.json` file
   `recipes.json` as our data store

   Code only in `recipes.js` file here?


*/




const chai = require('chai');
const chaiHttp = require('chai-http');
const server = require('../app');
const recipes = require('../recipes.json');
const should = chai.should();


chai.use(chaiHttp);

describe('express_recipes_filters', () => {

    it('Should return 400 for /recipes/shopping-list if no ids are passed in query',  (done) => {
        chai.request(server)
            .get('/recipes/shopping-list')
            .then(response => {
                response.status.should.eql(400);
                done();
            })
    });

    it('Should respond back with a 404 if none of the ids match',  (done) => {
        chai.request(server)
            .get('/recipes/shopping-list?ids=12,32,33,acasd')
            .then(response => {
                response.status.should.eql(404);
                response.text.should.eql('NOT_FOUND');
                done();
            })
    });

// failing the two unit tests below

    it('Should respond with the shopping list if the ids are valid and match is found',  (done) => {
        chai.request(server)
            .get('/recipes/shopping-list?ids=1')
            .then(response => {
                response.status.should.eql(200);
                response.body.should.eql(recipes[0].ingredients);
                done();
            })
    })

    it('Should respond with the correct shopping if the query sent is mixed',  (done) => {
        chai.request(server)
            .get('/recipes/shopping-list?ids=1,12,3')
            .then(response => {
                response.status.should.eql(200);
                response.body.should.eql([...recipes[0].ingredients, ...recipes[2].ingredients]);
                done();
            })
    })

});

