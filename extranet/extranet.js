const express = require('express')
const app = express()
const port = 8090;

app.use(express.json()) // for parsing application/json
app.use(express.urlencoded({ extended: true })) // for parsing application/x-www-form-urlencoded

app.post('/api/v1/payment/boleto',(req,res)=>{
          req.accepts('application/json')
          console.log(req.body)
          res.set('Content-Type', 'application/json')
          res.json(req.body).status(200)
})
app.listen(port,()=>{
          console.log(`Extranet está escutando a porta ${port}`)
})