USE [master]
GO
/****** Object:  Database [SimpleBlogDB]    Script Date: 1/19/2020 4:33:01 PM ******/
CREATE DATABASE [SimpleBlogDB]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'SimpleBlogDB', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\SimpleBlogDB.mdf' , SIZE = 4096KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'SimpleBlogDB_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\SimpleBlogDB_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [SimpleBlogDB] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [SimpleBlogDB].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [SimpleBlogDB] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [SimpleBlogDB] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [SimpleBlogDB] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [SimpleBlogDB] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [SimpleBlogDB] SET ARITHABORT OFF 
GO
ALTER DATABASE [SimpleBlogDB] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [SimpleBlogDB] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [SimpleBlogDB] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [SimpleBlogDB] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [SimpleBlogDB] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [SimpleBlogDB] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [SimpleBlogDB] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [SimpleBlogDB] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [SimpleBlogDB] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [SimpleBlogDB] SET  DISABLE_BROKER 
GO
ALTER DATABASE [SimpleBlogDB] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [SimpleBlogDB] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [SimpleBlogDB] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [SimpleBlogDB] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [SimpleBlogDB] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [SimpleBlogDB] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [SimpleBlogDB] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [SimpleBlogDB] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [SimpleBlogDB] SET  MULTI_USER 
GO
ALTER DATABASE [SimpleBlogDB] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [SimpleBlogDB] SET DB_CHAINING OFF 
GO
ALTER DATABASE [SimpleBlogDB] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [SimpleBlogDB] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [SimpleBlogDB] SET DELAYED_DURABILITY = DISABLED 
GO
USE [SimpleBlogDB]
GO
/****** Object:  Table [dbo].[Article]    Script Date: 1/19/2020 4:33:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Article](
	[title] [varchar](255) NOT NULL,
	[shortDescription] [varchar](255) NOT NULL,
	[author] [varchar](100) NOT NULL,
	[date] [smalldatetime] NOT NULL,
	[contentOfBlog] [text] NOT NULL,
	[status] [varchar](50) NOT NULL,
	[oldStatus] [varchar](50) NULL,
 CONSTRAINT [PK_Article_1] PRIMARY KEY CLUSTERED 
(
	[title] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Comment]    Script Date: 1/19/2020 4:33:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Comment](
	[id] [int] NOT NULL,
	[username] [varchar](100) NOT NULL,
	[date] [smalldatetime] NOT NULL,
	[comment] [text] NOT NULL,
	[title] [varchar](255) NOT NULL,
 CONSTRAINT [PK_Comment] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Registration]    Script Date: 1/19/2020 4:33:01 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Registration](
	[email] [varchar](100) NOT NULL,
	[name] [varchar](100) NOT NULL,
	[password] [varchar](255) NOT NULL,
	[role] [varchar](100) NOT NULL,
	[status] [varchar](100) NOT NULL,
 CONSTRAINT [PK_Registration] PRIMARY KEY CLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[Article] ([title], [shortDescription], [author], [date], [contentOfBlog], [status], [oldStatus]) VALUES (N'Assess Your Job Skills', N'Assess Your Job Skills', N'thinh@gmail.com', CAST(N'2020-01-19 00:38:00' AS SmallDateTime), N'Next, assess your job skills. As explained in a previous post, job skills are abilities and knowledge that are applied in the workplace. Most job skills are classified as either soft or hard. Soft skills are interpersonal skills that govern the way in which workers communicate, whereas hard skills are teachable technical skills. Both types of skills are important, though certain careers rely on one more than the other. In the hospitality industry, soft skills are particularly important because they allow workers to communicate with customers more effectively.', N'New', NULL)
INSERT [dbo].[Article] ([title], [shortDescription], [author], [date], [contentOfBlog], [status], [oldStatus]) VALUES (N'Assess Your Job Skills 1', N'Assess Your Job Skills', N'thinh@gmail.com', CAST(N'2020-01-19 00:38:00' AS SmallDateTime), N'Next, assess your job skills. As explained in a previous post, job skills are abilities and knowledge that are applied in the workplace. Most job skills are classified as either soft or hard. Soft skills are interpersonal skills that govern the way in which workers communicate, whereas hard skills are teachable technical skills. Both types of skills are important, though certain careers rely on one more than the other. In the hospitality industry, soft skills are particularly important because they allow workers to communicate with customers more effectively.', N'New', NULL)
INSERT [dbo].[Article] ([title], [shortDescription], [author], [date], [contentOfBlog], [status], [oldStatus]) VALUES (N'Assess Your Job Skills 2', N'Assess Your Job Skills', N'thinh@gmail.com', CAST(N'2020-01-19 00:38:00' AS SmallDateTime), N'Next, assess your job skills. As explained in a previous post, job skills are abilities and knowledge that are applied in the workplace. Most job skills are classified as either soft or hard. Soft skills are interpersonal skills that govern the way in which workers communicate, whereas hard skills are teachable technical skills. Both types of skills are important, though certain careers rely on one more than the other. In the hospitality industry, soft skills are particularly important because they allow workers to communicate with customers more effectively.', N'New', NULL)
INSERT [dbo].[Article] ([title], [shortDescription], [author], [date], [contentOfBlog], [status], [oldStatus]) VALUES (N'Assess Your Job Skills 3', N'Assess Your Job Skills', N'thinh@gmail.com', CAST(N'2020-01-19 00:38:00' AS SmallDateTime), N'Next, assess your job skills. As explained in a previous post, job skills are abilities and knowledge that are applied in the workplace. Most job skills are classified as either soft or hard. Soft skills are interpersonal skills that govern the way in which workers communicate, whereas hard skills are teachable technical skills. Both types of skills are important, though certain careers rely on one more than the other. In the hospitality industry, soft skills are particularly important because they allow workers to communicate with customers more effectively.', N'New', NULL)
INSERT [dbo].[Article] ([title], [shortDescription], [author], [date], [contentOfBlog], [status], [oldStatus]) VALUES (N'Assess Your Job Skills 4', N'Assess Your Job Skills', N'thinh@gmail.com', CAST(N'2020-01-19 00:38:00' AS SmallDateTime), N'Next, assess your job skills. As explained in a previous post, job skills are abilities and knowledge that are applied in the workplace. Most job skills are classified as either soft or hard. Soft skills are interpersonal skills that govern the way in which workers communicate, whereas hard skills are teachable technical skills. Both types of skills are important, though certain careers rely on one more than the other. In the hospitality industry, soft skills are particularly important because they allow workers to communicate with customers more effectively.', N'New', NULL)
INSERT [dbo].[Article] ([title], [shortDescription], [author], [date], [contentOfBlog], [status], [oldStatus]) VALUES (N'Assess Your Job Skills 5', N'Assess Your Job Skills', N'thinh@gmail.com', CAST(N'2020-01-19 00:38:00' AS SmallDateTime), N'Next, assess your job skills. As explained in a previous post, job skills are abilities and knowledge that are applied in the workplace. Most job skills are classified as either soft or hard. Soft skills are interpersonal skills that govern the way in which workers communicate, whereas hard skills are teachable technical skills. Both types of skills are important, though certain careers rely on one more than the other. In the hospitality industry, soft skills are particularly important because they allow workers to communicate with customers more effectively.', N'New', NULL)
INSERT [dbo].[Article] ([title], [shortDescription], [author], [date], [contentOfBlog], [status], [oldStatus]) VALUES (N'Assess Your Job Skills 6', N'Assess Your Job Skills', N'thinh@gmail.com', CAST(N'2020-01-19 00:38:00' AS SmallDateTime), N'Next, assess your job skills. As explained in a previous post, job skills are abilities and knowledge that are applied in the workplace. Most job skills are classified as either soft or hard. Soft skills are interpersonal skills that govern the way in which workers communicate, whereas hard skills are teachable technical skills. Both types of skills are important, though certain careers rely on one more than the other. In the hospitality industry, soft skills are particularly important because they allow workers to communicate with customers more effectively.', N'Active', NULL)
INSERT [dbo].[Article] ([title], [shortDescription], [author], [date], [contentOfBlog], [status], [oldStatus]) VALUES (N'Assess Your Job Skills 7', N'Assess Your Job Skills', N'thinh@gmail.com', CAST(N'2020-01-19 00:38:00' AS SmallDateTime), N'Next, assess your job skills. As explained in a previous post, job skills are abilities and knowledge that are applied in the workplace. Most job skills are classified as either soft or hard. Soft skills are interpersonal skills that govern the way in which workers communicate, whereas hard skills are teachable technical skills. Both types of skills are important, though certain careers rely on one more than the other. In the hospitality industry, soft skills are particularly important because they allow workers to communicate with customers more effectively.', N'New', NULL)
INSERT [dbo].[Article] ([title], [shortDescription], [author], [date], [contentOfBlog], [status], [oldStatus]) VALUES (N'Assess Your Personal Interests', N'Assess Your Personal Interests', N'thinh@gmail.com', CAST(N'2020-01-19 00:41:00' AS SmallDateTime), N'It is also a good idea to assess your personal values. As a worker for a company, you will have to follow that values. If those values are not aligned with your own personal values, it could create a conflict that hinders your work performance.', N'Delete', N'Active')
INSERT [dbo].[Article] ([title], [shortDescription], [author], [date], [contentOfBlog], [status], [oldStatus]) VALUES (N'Assess Your Personal Interests 1', N'Assess Your Personal Interests', N'thinh@gmail.com', CAST(N'2020-01-19 00:41:00' AS SmallDateTime), N'It is also a good idea to assess your personal values. As a worker for a company, you will have to follow that values. If those values are not aligned with your own personal values, it could create a conflict that hinders your work performance.', N'Delete', N'Active')
INSERT [dbo].[Article] ([title], [shortDescription], [author], [date], [contentOfBlog], [status], [oldStatus]) VALUES (N'Assess Your Personal Interests 2', N'Assess Your Personal Interests', N'thinh@gmail.com', CAST(N'2020-01-19 00:41:00' AS SmallDateTime), N'It is also a good idea to assess your personal values. As a worker for a company, you will have to follow that values. If those values are not aligned with your own personal values, it could create a conflict that hinders your work performance.', N'Delete', N'Delete')
INSERT [dbo].[Article] ([title], [shortDescription], [author], [date], [contentOfBlog], [status], [oldStatus]) VALUES (N'Assess Your Personal Interests 3', N'Assess Your Personal Interests', N'thinh@gmail.com', CAST(N'2020-01-19 00:41:00' AS SmallDateTime), N'It is also a good idea to assess your personal values. As a worker for a company, you will have to follow that values. If those values are not aligned with your own personal values, it could create a conflict that hinders your work performance.', N'Delete', N'Delete')
INSERT [dbo].[Article] ([title], [shortDescription], [author], [date], [contentOfBlog], [status], [oldStatus]) VALUES (N'Assess Your Personal Interests 4', N'Assess Your Personal Interests', N'thinh@gmail.com', CAST(N'2020-01-19 00:41:00' AS SmallDateTime), N'It is also a good idea to assess your personal values. As a worker for a company, you will have to follow that values. If those values are not aligned with your own personal values, it could create a conflict that hinders your work performance.', N'Active', NULL)
INSERT [dbo].[Article] ([title], [shortDescription], [author], [date], [contentOfBlog], [status], [oldStatus]) VALUES (N'Assess Your Values', N'Assess Your Values', N'thinh@gmail.com', CAST(N'2020-01-19 00:39:00' AS SmallDateTime), N'Next, assess your job skills. As explained in a previous post, job skills are abilities and knowledge that are applied in the workplace. Most job skills are classified as either soft or hard. Soft skills are interpersonal skills that govern the way in which workers communicate, whereas hard skills are teachable technical skills. Both types of skills are important, though certain careers rely on one more than the other. In the hospitality industry, soft skills are particularly important because they allow workers to communicate with customers more effectively.', N'Active', NULL)
INSERT [dbo].[Article] ([title], [shortDescription], [author], [date], [contentOfBlog], [status], [oldStatus]) VALUES (N'Assess Your Values 1', N'Assess Your Values', N'thinh@gmail.com', CAST(N'2020-01-19 00:39:00' AS SmallDateTime), N'Next, assess your job skills. As explained in a previous post, job skills are abilities and knowledge that are applied in the workplace. Most job skills are classified as either soft or hard. Soft skills are interpersonal skills that govern the way in which workers communicate, whereas hard skills are teachable technical skills. Both types of skills are important, though certain careers rely on one more than the other. In the hospitality industry, soft skills are particularly important because they allow workers to communicate with customers more effectively.', N'New', NULL)
INSERT [dbo].[Article] ([title], [shortDescription], [author], [date], [contentOfBlog], [status], [oldStatus]) VALUES (N'Assess Your Values 2', N'Assess Your Values', N'thinh@gmail.com', CAST(N'2020-01-19 00:39:00' AS SmallDateTime), N'Next, assess your job skills. As explained in a previous post, job skills are abilities and knowledge that are applied in the workplace. Most job skills are classified as either soft or hard. Soft skills are interpersonal skills that govern the way in which workers communicate, whereas hard skills are teachable technical skills. Both types of skills are important, though certain careers rely on one more than the other. In the hospitality industry, soft skills are particularly important because they allow workers to communicate with customers more effectively.', N'New', NULL)
INSERT [dbo].[Article] ([title], [shortDescription], [author], [date], [contentOfBlog], [status], [oldStatus]) VALUES (N'Assess Your Values 3', N'Assess Your Values', N'thinh@gmail.com', CAST(N'2020-01-19 00:39:00' AS SmallDateTime), N'Next, assess your job skills. As explained in a previous post, job skills are abilities and knowledge that are applied in the workplace. Most job skills are classified as either soft or hard. Soft skills are interpersonal skills that govern the way in which workers communicate, whereas hard skills are teachable technical skills. Both types of skills are important, though certain careers rely on one more than the other. In the hospitality industry, soft skills are particularly important because they allow workers to communicate with customers more effectively.', N'New', NULL)
INSERT [dbo].[Article] ([title], [shortDescription], [author], [date], [contentOfBlog], [status], [oldStatus]) VALUES (N'Assess Your Values 4', N'Assess Your Values', N'thinh@gmail.com', CAST(N'2020-01-19 00:39:00' AS SmallDateTime), N'Next, assess your job skills. As explained in a previous post, job skills are abilities and knowledge that are applied in the workplace. Most job skills are classified as either soft or hard. Soft skills are interpersonal skills that govern the way in which workers communicate, whereas hard skills are teachable technical skills. Both types of skills are important, though certain careers rely on one more than the other. In the hospitality industry, soft skills are particularly important because they allow workers to communicate with customers more effectively.', N'New', NULL)
INSERT [dbo].[Article] ([title], [shortDescription], [author], [date], [contentOfBlog], [status], [oldStatus]) VALUES (N'Assess Your Values 5', N'Assess Your Values', N'thinh@gmail.com', CAST(N'2020-01-19 00:39:00' AS SmallDateTime), N'Next, assess your job skills. As explained in a previous post, job skills are abilities and knowledge that are applied in the workplace. Most job skills are classified as either soft or hard. Soft skills are interpersonal skills that govern the way in which workers communicate, whereas hard skills are teachable technical skills. Both types of skills are important, though certain careers rely on one more than the other. In the hospitality industry, soft skills are particularly important because they allow workers to communicate with customers more effectively.', N'New', NULL)
INSERT [dbo].[Article] ([title], [shortDescription], [author], [date], [contentOfBlog], [status], [oldStatus]) VALUES (N'Assess Your Values 6', N'Assess Your Values', N'thinh@gmail.com', CAST(N'2020-01-19 00:39:00' AS SmallDateTime), N'Next, assess your job skills. As explained in a previous post, job skills are abilities and knowledge that are applied in the workplace. Most job skills are classified as either soft or hard. Soft skills are interpersonal skills that govern the way in which workers communicate, whereas hard skills are teachable technical skills. Both types of skills are important, though certain careers rely on one more than the other. In the hospitality industry, soft skills are particularly important because they allow workers to communicate with customers more effectively.', N'New', NULL)
INSERT [dbo].[Article] ([title], [shortDescription], [author], [date], [contentOfBlog], [status], [oldStatus]) VALUES (N'Assess Your Values 7', N'Assess Your Values', N'thinh@gmail.com', CAST(N'2020-01-19 00:39:00' AS SmallDateTime), N'Next, assess your job skills. As explained in a previous post, job skills are abilities and knowledge that are applied in the workplace. Most job skills are classified as either soft or hard. Soft skills are interpersonal skills that govern the way in which workers communicate, whereas hard skills are teachable technical skills. Both types of skills are important, though certain careers rely on one more than the other. In the hospitality industry, soft skills are particularly important because they allow workers to communicate with customers more effectively.', N'New', NULL)
INSERT [dbo].[Article] ([title], [shortDescription], [author], [date], [contentOfBlog], [status], [oldStatus]) VALUES (N'Assess Your Values 8', N'Assess Your Values', N'thinh@gmail.com', CAST(N'2020-01-19 00:39:00' AS SmallDateTime), N'Next, assess your job skills. As explained in a previous post, job skills are abilities and knowledge that are applied in the workplace. Most job skills are classified as either soft or hard. Soft skills are interpersonal skills that govern the way in which workers communicate, whereas hard skills are teachable technical skills. Both types of skills are important, though certain careers rely on one more than the other. In the hospitality industry, soft skills are particularly important because they allow workers to communicate with customers more effectively.', N'New', NULL)
INSERT [dbo].[Article] ([title], [shortDescription], [author], [date], [contentOfBlog], [status], [oldStatus]) VALUES (N'Assess Your Values 9', N'Assess Your Values', N'thinh@gmail.com', CAST(N'2020-01-19 00:39:00' AS SmallDateTime), N'Next, assess your job skills. As explained in a previous post, job skills are abilities and knowledge that are applied in the workplace. Most job skills are classified as either soft or hard. Soft skills are interpersonal skills that govern the way in which workers communicate, whereas hard skills are teachable technical skills. Both types of skills are important, though certain careers rely on one more than the other. In the hospitality industry, soft skills are particularly important because they allow workers to communicate with customers more effectively.', N'New', NULL)
INSERT [dbo].[Article] ([title], [shortDescription], [author], [date], [contentOfBlog], [status], [oldStatus]) VALUES (N'Create a List of Dream Careers', N'Create a List of Dream Careers', N'thinh@gmail.com', CAST(N'2020-01-19 00:40:00' AS SmallDateTime), N'After assessing your personal interests, job skills and personal values, create a list of your ideal dream careers. Add at least 10 careers to this list, after which you begin searching for specific information about them, including how much they pay, educational requirements, projected future growth, average time off, training requirements and more. By looking into the specific details of each career, you''ll have a better idea of which one is right for you.', N'Active', NULL)
INSERT [dbo].[Article] ([title], [shortDescription], [author], [date], [contentOfBlog], [status], [oldStatus]) VALUES (N'Train the Trainer', N'B''mart', N'thinh@gmail.com', CAST(N'2020-01-19 16:19:00' AS SmallDateTime), N'abc', N'Delete', N'New')
INSERT [dbo].[Article] ([title], [shortDescription], [author], [date], [contentOfBlog], [status], [oldStatus]) VALUES (N'What Career is Right for Me?', N'Choosing a career is arguably one of the most important decisions you will make in your life. According to a Pew survey, the average U.S. worker clocks 38.7 hours a week.', N'duy@gmail.com', CAST(N'2020-01-19 00:36:00' AS SmallDateTime), N'Start by assessing your personal interests and what you enjoy doing. Financial compensation is important when choosing a career, but enjoyment is even more important. If you are an avid photographer who enjoys taking photos of scenic landscapes, maybe wedding photography is the right career path for you. On the other hand, if you enjoy meeting and interacting with new people, perhaps you should choose a career in the hospitality industry. Regardless, the first step to choosing the right career is to assess your personal interests.', N'New', NULL)
INSERT [dbo].[Registration] ([email], [name], [password], [role], [status]) VALUES (N'duy@gmail.com', N'manh duy', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'member', N'New')
INSERT [dbo].[Registration] ([email], [name], [password], [role], [status]) VALUES (N'hoangtd@gmail.com', N'duy hoang', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'admin', N'New')
INSERT [dbo].[Registration] ([email], [name], [password], [role], [status]) VALUES (N'thinh@gmail.com', N'thinh pham', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'member', N'New')
ALTER TABLE [dbo].[Article]  WITH CHECK ADD  CONSTRAINT [FK_Article_Registration] FOREIGN KEY([author])
REFERENCES [dbo].[Registration] ([email])
GO
ALTER TABLE [dbo].[Article] CHECK CONSTRAINT [FK_Article_Registration]
GO
ALTER TABLE [dbo].[Comment]  WITH CHECK ADD  CONSTRAINT [FK_Comment_Article] FOREIGN KEY([title])
REFERENCES [dbo].[Article] ([title])
GO
ALTER TABLE [dbo].[Comment] CHECK CONSTRAINT [FK_Comment_Article]
GO
ALTER TABLE [dbo].[Comment]  WITH CHECK ADD  CONSTRAINT [FK_Comment_Registration] FOREIGN KEY([username])
REFERENCES [dbo].[Registration] ([email])
GO
ALTER TABLE [dbo].[Comment] CHECK CONSTRAINT [FK_Comment_Registration]
GO
USE [master]
GO
ALTER DATABASE [SimpleBlogDB] SET  READ_WRITE 
GO
