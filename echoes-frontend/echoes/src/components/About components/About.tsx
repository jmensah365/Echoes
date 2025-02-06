import React from 'react'
import Blog from '../../blog/Blog.tsx'
import { Box, Container, Grid2, Typography } from '@mui/material'
import Feature1 from '../Cards/AboutPageCards/Feature1.tsx'
import Feature2 from '../Cards/AboutPageCards/Feature2.tsx'
import Feature3 from '../Cards/AboutPageCards/Feature3.tsx'

function About() {

    return (
        <>
            {/* <Blog/> */}
            <Typography variant='h1'>
                About Echoes
            </Typography>
            <Typography >
                The app is a personal memory-streaming platform, allowing you to view and revisit your personal memories, photos and videos from your life.
                Similar to how streaming sites display media content
            </Typography>
            <Container component='main' sx={{ flexGrow: 1, py: 4 }}>
                <Box
                sx={{
                    display: 'flex',
                    overflowX: 'auto',
                    gap: 10, 
                    py: 2,
                }}
                >
                    <Box><Feature1/></Box>
                    <Box><Feature2/></Box>
                    <Box><Feature3/></Box>
                </Box>
            </Container>
        </>
    )
}

export default About