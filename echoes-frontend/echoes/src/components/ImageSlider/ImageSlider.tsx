import {ArrowBackIosNew, ArrowForwardIos, Circle, CircleRounded, TripOrigin } from "@mui/icons-material";
import { Container, IconButton } from "@mui/material";
import { useState } from "react"

type ImageSliderProps = {
    imageUrls: string[]
}
export function ImageSlider({imageUrls}: ImageSliderProps) {
    const [imageIndex, setImageIndex] = useState(0);

    function showPrevImage(){
        setImageIndex(index => {
            if (index === 0) return imageUrls.length - 1
            return index - 1
        })
    }

    function showNextImage(){
        setImageIndex(index => {
            if (index === imageUrls.length - 1) return 0
            return index + 1
        })
    }

    return (
    <Container style={{width: "100%" , height: "100%", position: 'relative'}}>
        <div style={{width: "100%" , height: "100%", display: 'flex', overflow: 'hidden'}}>
            {imageUrls.map(url => (
                <img key={url} src={url} className="img-slider-img" style={{translate: `${-100 * imageIndex}%` }}/>
            ))}
        </div>
        <IconButton onClick={showPrevImage} className='img-slider-btn' color="info" style={{left: '0'}}>
            <ArrowBackIosNew />
        </IconButton>
        <IconButton onClick={showNextImage} className='img-slider-btn' color="info" style={{right: '0'}}>
            <ArrowForwardIos />
        </IconButton>
        <Container
            style={{
                position: 'absolute',
                bottom: '.5rem',
                left: '88%',
                translate: '-50%',
                display: 'flex',
                gap: '.25rem',
            }}>
            {imageUrls.map((_, index) => (
                <button key={index} className='img-slider-dot-btn' onClick={() => setImageIndex(index)}>{index == imageIndex ? <TripOrigin/> : <Circle/>}</button>
            ))}
        </Container>
    </Container>
    )
}