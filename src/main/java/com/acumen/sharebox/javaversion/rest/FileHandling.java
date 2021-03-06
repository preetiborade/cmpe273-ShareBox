package com.acumen.sharebox.javaversion.rest;

import java.io.File;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
 
@Path("files")
public class FileHandling {
	
	@GET
    @Path("/download")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response downloadObjects(){
    	
    	String output="Files downloaded at location: /Users/preetiborade/Documents";
    	AwsS3BucketHandling awsS3BucketHandling=new AwsS3BucketHandling();
    	output=awsS3BucketHandling.getS3BucketObjects("/Users/preetiborade/Documents");
    	System.out.println("OBJECT NAME::::: "+output);
    	System.out.println("inside REST /download");
    	return Response.status(200).entity(output).build();
    }  

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(
            @FormDataParam("file") File fileobject,
            @FormDataParam("file") FormDataContentDisposition contentDispositionHeader) {
    	System.out.println("inside class uploadFile###################");
    	AwsS3BucketHandling awsS3BucketHandling=new AwsS3BucketHandling();
    	System.out.println("filename: $$$$ "+contentDispositionHeader.getFileName());
    	String output=awsS3BucketHandling.addS3BucketObjects(fileobject,contentDispositionHeader.getFileName());
        return Response.status(200).entity(output).build();
    }
    
    @DELETE
    @Path("/delete/{objectkey}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response deleteObject(
    		@PathParam("objectkey") String key){
    	AwsS3BucketHandling awsS3BucketHandling=new AwsS3BucketHandling();
    	System.out.println("key::::::: "+key);
    	String output=awsS3BucketHandling.deleteS3BucketObjects(key);
    	return Response.status(200).entity(output).build();
    }  

    
    
    @POST
    @Path("/uploadDirectory")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadDirectory(
            @PathParam("file") File fileobject) {
 
    	System.out.println("inside class uploadFile###################");
    	AwsS3BucketHandling awsS3BucketHandling=new AwsS3BucketHandling();
    	System.out.println("filename: $$$$ "+fileobject.getName());
    	String output=awsS3BucketHandling.addFolderS3BucketObjects(fileobject,fileobject.getName());
        return Response.status(200).entity(output).build();
    }    
}