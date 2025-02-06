import { Card, CardActionArea, CardActions, CardContent, Typography } from "@mui/material"

function Feature1() {
    return (
        <>
            <Card>
                <CardContent>
                    <Typography>
                        View all your memories in one place!
                    </Typography>
                </CardContent>
                <CardActionArea>
                    <CardActions>
                        Add a memory
                    </CardActions>
                </CardActionArea>
            </Card>
        </>
    )
}

export default Feature1