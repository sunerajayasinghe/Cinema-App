import React from "react";
import { Grid } from "@mui/material";
import SizeAvatars from "../Components/Avatar";
function UserProfilePage(){
    return(
        <Grid display={'flex'} sx={{justifyContent:'center',alignItems:'center'}}>
            <SizeAvatars/>
        </Grid>
    );
}

export default UserProfilePage;