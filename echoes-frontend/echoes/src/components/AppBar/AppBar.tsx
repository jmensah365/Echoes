import { Button, AppBar, Box, FormControl, OutlinedInput, InputAdornment, IconButton, Toolbar, Typography } from '@mui/material';
import SearchRoundedIcon from '@mui/icons-material/SearchRounded';
import echoesLogoNoBackground from '../../assets/EchoesNoBackground.png';
import { styled, alpha } from '@mui/material';
import { useNavigate } from 'react-router-dom';

// StyledToolbar component for consistent styling
const StyledToolbar = styled(Toolbar)(({ theme }) => ({
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'space-between',
    flexShrink: 0,
    borderRadius: `calc(${theme.shape.borderRadius}px + 8px)`,
    backdropFilter: 'blur(24px)',
    border: '1px solid',
    borderColor: (theme.vars || theme).palette.divider,
    backgroundColor: alpha(theme.palette.common.black, 0.8), // Black background with 80% opacity
    boxShadow: (theme.vars || theme).shadows[1],
    padding: '8px 12px',
}));


export function Search() {
    return (
        <FormControl sx={{ width: { xs: '100%', md: '25ch' } }} variant="outlined">
            <OutlinedInput
                size="small"
                id="search"
                placeholder="Search…"
                sx={{ flexGrow: 1, color: 'white' }}
                startAdornment={
                    <InputAdornment position="start" sx={{ color: 'white' }}>
                        <SearchRoundedIcon fontSize="small" />
                    </InputAdornment>
                }
                inputProps={{
                    'aria-label': 'search',
                }}
            />
        </FormControl>
    );
}


const AppBarComponent = () => {
    const navigate = useNavigate()
    return (
        <AppBar
            position="fixed"
            enableColorOnDark
            sx={{
                boxShadow: 0,
                bgcolor: 'transparent',
                backgroundImage: 'none',
                mt: 'calc(var(--template-frame-height, 0px) + 32px)',
            }}
        >
            <StyledToolbar>
                {/* Logo */}
                <Box sx={{ display: 'flex', alignItems: 'center' }}>
                    <IconButton edge="start" sx={{ padding: 0 }}>
                        <img src={echoesLogoNoBackground} alt="Echoes Logo" aria-label="echoesLogo" style={{ height: '40px' }} />
                    </IconButton>
                </Box>

                {/* Navigation Buttons */}
                <Box sx={{ display: { xs: 'none', md: 'center' }, alignItems: 'center', gap: 2 }}>
                    <Button onClick={() => navigate('/')} size="small" variant="text" color="inherit">
                        Home
                    </Button>
                    <Typography color='primary'>
                        |
                    </Typography>
                    <Button size="small" variant="text" color="inherit">
                        Memories
                    </Button>
                    <Typography color='primary'>
                        |
                    </Typography>
                    <Button size="small" variant="text" color="inherit">
                        Albums
                    </Button>
                    <Typography color='primary'>
                        |
                    </Typography>
                    <Button onClick={() => navigate('/about')} size="small" variant="text" color="inherit">
                        About
                    </Button>
                </Box>

                {/* Search Component */}
                <Box sx={{ display: { xs: 'none', md: 'block' }, borderRadius: '32px', border: 'solid white' }}>
                    <Search />
                </Box>
            </StyledToolbar>
        </AppBar>
    );
};

export default AppBarComponent