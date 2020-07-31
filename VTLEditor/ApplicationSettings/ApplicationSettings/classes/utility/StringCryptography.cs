using System;
using System.Linq;
using System.Security.Cryptography;
using System.Text;

namespace ApplicationSettings.classes.utility
{
    public class StringCryptography
    {
        private static string _clearKey = "m3rB@q6=(ZnS}?VI";

        public static string Encrypt(string toEncrypt)
        {
            byte[] keyArray = getMD5HashKey(_clearKey).Take(24).ToArray();//24 bit key
            byte[] toEncryptArray = UTF8Encoding.UTF8.GetBytes(toEncrypt);

            TripleDESCryptoServiceProvider tdes = new TripleDESCryptoServiceProvider();
            //set the secret key for the tripleDES algorithm
            tdes.Key = keyArray;
            //mode of operation. there are other 4 modes.
            //We choose ECB(Electronic code Book)
            tdes.Mode = CipherMode.ECB;
            //padding mode(if any extra byte added)

            tdes.Padding = PaddingMode.PKCS7;

            ICryptoTransform cTransform = tdes.CreateEncryptor();
            //transform the specified region of bytes array to resultArray
            byte[] resultArray =
              cTransform.TransformFinalBlock(toEncryptArray, 0,
              toEncryptArray.Length);
            //Release resources held by TripleDes Encryptor
            tdes.Clear();
            //Return the encrypted data into unreadable string format
            return Convert.ToBase64String(resultArray, 0, resultArray.Length);
        }

        public static string Decrypt(string cipherString)
        {
            byte[] keyArray = getMD5HashKey(_clearKey).Take(24).ToArray();//24 bit key
            //get the byte code of the string

            byte[] toEncryptArray = Convert.FromBase64String(cipherString);

            TripleDESCryptoServiceProvider tdes = new TripleDESCryptoServiceProvider();
            //set the secret key for the tripleDES algorithm
            tdes.Key = keyArray;
            //mode of operation. there are other 4 modes. 
            //We choose ECB(Electronic code Book)

            tdes.Mode = CipherMode.ECB;
            //padding mode(if any extra byte added)
            tdes.Padding = PaddingMode.PKCS7;

            ICryptoTransform cTransform = tdes.CreateDecryptor();
            byte[] resultArray = cTransform.TransformFinalBlock(toEncryptArray, 0, toEncryptArray.Length);

            //Release resources held by TripleDes Encryptor                
            tdes.Clear();
            //return the Clear decrypted TEXT
            return UTF8Encoding.UTF8.GetString(resultArray);
        }


        private static byte[] getMD5HashKey(string input)
        {
            try
            {
                System.Security.Cryptography.MD5CryptoServiceProvider x = new System.Security.Cryptography.MD5CryptoServiceProvider();
                byte[] bs = System.Text.Encoding.UTF8.GetBytes(input);
                bs = x.ComputeHash(bs);
                System.Text.StringBuilder s = new System.Text.StringBuilder();
                foreach (byte b in bs)
                {
                    s.Append(b.ToString("x2").ToLower());
                }
                string hash = s.ToString();

                byte[] bytes = new byte[hash.Length * sizeof(char)];
                System.Buffer.BlockCopy(hash.ToCharArray(), 0, bytes, 0, bytes.Length);

                return bytes;
            }
            catch (Exception ex)
            {
                throw new Exception("Error, [GetDataDLL.Tools.ToolsSet.StringCryptography.getMD5Hash] " + ex.Message);
            }
        }
    }
}
