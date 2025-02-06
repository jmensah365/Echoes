import { useState } from 'react'
import './App.css'
import About from './components/About components/About'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import AppBarComponent from './components/AppBar/AppBar'
import HomePage from './components/Home/HomePage'

function App() {

  return (
    <>
      <BrowserRouter>
        <AppBarComponent/>
          <Routes>
            <Route path='/' element={<HomePage/>} />
            <Route path='/about' element={<About/>} />
          </Routes>
      </BrowserRouter>
    </>
  )
}

export default App
